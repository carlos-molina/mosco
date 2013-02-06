package ncl.b1037041.bpmn.choreographies;

import java.io.File;
import java.util.*;

import ncl.b1037041.LTL.entites.BPMNChoreography;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import simulator.jim.choreographies.entites.*;
import simulator.jim.choreographies.interfaces.AbstractExclusiveGateway;
import simulator.jim.choreographies.interfaces.InterfaceFlowNode;
import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;
import simulator.jim.tools.Tool;

public class Context {
	
	private static Context context;
	
	//One route
	private static final List<InterfaceFlowNode> pathList = new LinkedList<InterfaceFlowNode>();
	//for each path, define a stack
	private static final Stack<InterfaceLogicalParent> symbolStack = new Stack<InterfaceLogicalParent>();
	//for each path, define a map to identify different route
	private static final Map<InterfaceLogicalParent, Boolean> symbolMap = new HashMap<InterfaceLogicalParent, Boolean>();
	
	private Definitions definitions = new Definitions();
	private InterfaceFlowNode startEvent;
	private Set<Queue<InterfaceFlowNode>> allPath = new HashSet<Queue<InterfaceFlowNode>>();
	private Set<Queue<InterfaceFlowNode>> validPath;
	private Set<Queue<InterfaceFlowNode>> loopPath = new HashSet<Queue<InterfaceFlowNode>>();
	
	private int id;
	
	
	private Context(int id) {this.id = id;}
	
	public int getId() {
		return this.id;
	}
	
	public InterfaceFlowNode getStartEvent() {
		return startEvent;
	}
	
	public static void clearAll() {
		if(context != null) {
			context = null;
		}		
	}
	
	public static Context init(BPMNChoreography bpmn) throws Exception {
		clearAll();
		if(context == null) {
			context = new Context(bpmn.getId());
		}
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(bpmn.getFilePath()));
		
		return initContext(document);
	}
	
	@SuppressWarnings("unchecked")
	private static Context initContext(Document document) {
		Element root = document.getRootElement();
		
		Iterator<Element> it = root.elementIterator();

		Element element = null;
		while(it.hasNext()) {
			element = it.next();

			if("message".equals(element.getName())) {
				Message m = new Message();
				//System.out.println(element.attributeValue("id"));
				m.setId(element.attributeValue("id"));
				m.setName(element.attributeValue("name"));				
				String itemRefId = element.attributeValue("itemRef");
				m.setItemRefId(itemRefId);
				m.setItemRef(context.definitions.searchSingleItemDefinition(itemRefId));
				context.definitions.addMessage(m);
				continue;
			}
			
			if("itemDefinition".equals(element.getName())) {
				ItemDefinition d = new ItemDefinition();
				d.setId(element.attributeValue("id"));
				d.setItemKind(element.attributeValue("itemKind"));
				d.setStructureRef(element.attributeValue("structureRef"));
				context.definitions.searchAndAddToSingleMessage(d);
				context.definitions.addItemDefinition(d);
				continue;
			}
			
			if("choreography".equals(element.getName())) {
				Choreography c = context.definitions.getChoreography();
				c.setChoreographyElemwnt(element);
				Iterator<Element> it_c = element.elementIterator();
				while(it_c.hasNext()) {
					element = it_c.next();
					//System.out.println(element.getName());
					if("participant".equals(element.getName())) {
						Participant p = new Participant();
						p.setId(element.attributeValue("id"));
						p.setName(element.attributeValue("name"));
						c.addParticipant(p);
						continue;
					}
				}
			}						
		}
		
		Choreography chor = context.definitions.getChoreography();
		
		Iterator<Element> it_par = chor.getChoreographyElemwnt().elementIterator();
		while(it_par.hasNext()) {
			element = it_par.next();
			if("participant".equals(element.getName())) {
				Participant p = new Participant();
				p.setId(element.attributeValue("id"));
				p.setName(element.attributeValue("name"));
				chor.addParticipant(p);
			}
		}
		
		Iterator<Element> it_msg = chor.getChoreographyElemwnt().elementIterator();
		while(it_msg.hasNext()) {
			element = it_msg.next();
			if("messageFlow".equals(element.getName())) {
				MessageFlow mf = new MessageFlow();
				mf.setId(element.attributeValue("id"));
				mf.setName(element.attributeValue("name"));
				String messageRefId = element.attributeValue("messageRef");
				mf.setMessageRef(chor.searchSingleMessage(messageRefId));
				chor.addMessageFlow(mf);
			}
		}
		
		Iterator<Element> it_seq = chor.getChoreographyElemwnt().elementIterator();
		while(it_seq.hasNext()) {
			element = it_seq.next();
			if("sequenceFlow".equals(element.getName())) {
				SequenceFlow sf = new SequenceFlow();
				sf.setId(element.attributeValue("id"));
				sf.setName(element.attributeValue("name"));
				String sourceRefId = element.attributeValue("sourceRef");
				sf.setSourceRefId(sourceRefId);
				String targetRefId = element.attributeValue("targetRef");
				sf.setSourceRefId(targetRefId);
				chor.addSequenceFlow(sf);
			}
		}

		Iterator<Element> it_start_end = chor.getChoreographyElemwnt().elementIterator();
		while(it_start_end.hasNext()) {
			element = it_start_end.next();
			if("startEvent".equals(element.getName())) {
				StartEvent startEvent = new StartEvent();
				startEvent.setId(element.attributeValue("id"));
				startEvent.setName(element.attributeValue("name"));
				SequenceFlow outgoing = chor.searchSingleSequenceFlow(element.elementText("outgoing"));
				startEvent.addOutgoing(outgoing);
				outgoing.setSourceRef(startEvent);
				chor.setStartEvent(startEvent);
				context.startEvent = startEvent;
			}
			if("endEvent".equals(element.getName())) {
				EndEvent endEvent = new EndEvent();
				endEvent.setId(element.attributeValue("id"));
				endEvent.setName(element.attributeValue("name"));
				SequenceFlow incoming = chor.searchSingleSequenceFlow(element.elementText("incoming"));
				endEvent.addIncoming(incoming);
				incoming.setTargetRef(endEvent);
				chor.setEndEvent(endEvent);
			}
		}

		Iterator<Element> it_c = chor.getChoreographyElemwnt().elementIterator();
		while(it_c.hasNext()) {
			element = it_c.next();
			if("choreographyTask".equals(element.getName())) {
				ChoreographyTask ct = new ChoreographyTask();
				ct.setId(element.attributeValue("id"));
				ct.setName(element.attributeValue("name"));
				
				Participant initPar = chor.searchSingleParticipant(element.attributeValue("initiatingParticipantRef"));
				ct.setInitiatingParticipantRef(initPar);
			
				SequenceFlow outgoing = chor.searchSingleSequenceFlow(element.elementText("outgoing"));
				ct.addOutgoing(outgoing);
				outgoing.setSourceRef(ct);
				
				ct.setMessageFlowRef(chor.searchSingleMessageFlow(element.elementText("messageFlowRef")));
				chor.addChoreographyTask(ct);
				
				Iterator<Element> it_p = element.elementIterator();
				Element children = null;
				while(it_p.hasNext()) {
					children = it_p.next();
					if("participantRef".equals(children.getName())) {
						Participant par = chor.searchSingleParticipant(children.getText());
						if(!initPar.equals(par)) {
							ct.addParticipant(par);
						}						
					}
					if("incoming".equals(children.getName())) {
						SequenceFlow incoming = chor.searchSingleSequenceFlow(children.getText());
						ct.addIncoming(incoming);
						incoming.setTargetRef(ct);
					}
				}				
			}
			if("exclusiveGateway".equals(element.getName())) {
				AbstractExclusiveGateway eg = null;						
				Iterator<Element> it_ex_count = element.elementIterator();
				int in_count = 0;
				int out_count = 0;
				Element children = null;
				while(it_ex_count.hasNext()) {
					children = it_ex_count.next();
					if("incoming".equals(children.getName())) {
						in_count++;
					}
					if("outgoing".equals(children.getName())) {
						out_count++;
					}
				}
				
				if(in_count == 1) {
					eg = AbstractExclusiveGateway.getExclusiveGateway(AbstractExclusiveGateway.EXCLUSIVE_GATEWAY_SPLIT);
				}
				if(out_count == 1) {
					eg = AbstractExclusiveGateway.getExclusiveGateway(AbstractExclusiveGateway.EXCLUSIVE_GATEWAY_MERGE);
				}
				eg.setId(element.attributeValue("id"));
				
				Iterator<Element> it_in_out = element.elementIterator();
				while(it_in_out.hasNext()) {
					element = it_in_out.next();
					if("incoming".equals(element.getName())) {
						SequenceFlow incoming = chor.searchSingleSequenceFlow(element.getText());
						eg.addIncoming(incoming);
						incoming.setTargetRef(eg);
					}
					if("outgoing".equals(element.getName())) {
						SequenceFlow outgoing = chor.searchSingleSequenceFlow(element.getText());
						eg.addOutgoing(outgoing);
						outgoing.setSourceRef(eg);
					}
				}
			}
		}
		context.startEvent.goThrough();
		
		//Identify parent and path index of each element
		context.handleEachElement();
		
		return context;
	}
	
	private void handleEachElement() {
		this.handlePath(context.getValidPath());
		this.handlePath(context.getLoopPath());
	}
	
	private void handlePath(Set<Queue<InterfaceFlowNode>> queues) {
		Iterator<Queue<InterfaceFlowNode>> it = queues.iterator();
		Queue<InterfaceFlowNode> path;
		while(it.hasNext()) {
			path = it.next();
			this.clearAndInit(path);
			for(int i=0;i<pathList.size();i++) {
				InterfaceFlowNode con = pathList.get(i);				
				con.handleElementInEachPath(pathList, symbolStack, symbolMap);
			}
			
			/*System.out.println();
			Iterator<InterfaceFlowConnector> ff = pathList.iterator();			
			while(ff.hasNext()) {
				InterfaceFlowConnector oo = (InterfaceFlowConnector)ff.next();
				if(oo.getContainerInfo() != null) {
					System.out.println(oo + " -> " + oo.getContainerInfo().getContainer() + " " + oo.getContainerInfo().getPathIndex());
				}	
			}
			System.out.println();*/
		}
	}
	
	private void clearAndInit(Queue<InterfaceFlowNode> path) {
		pathList.clear();
		pathList.addAll(path);
		symbolStack.clear();
		symbolMap.clear();
	}
	
	public static Context getContext() {
		if(context == null) {
			throw new RuntimeException("Context has not been initialized, pass target BPMN2 file into the init method");
		}
		return context;
	}
	
	public Definitions getDefinitions() {
		return this.definitions;
	}
	
	public Set<Queue<InterfaceFlowNode>> getAllPath() {
		return this.allPath;
	}
	
	public void addPath(Queue<InterfaceFlowNode> normalPath) {
		this.allPath.add(normalPath);
	}
	
	public Set<Queue<InterfaceFlowNode>> getLoopPath() {
		return this.loopPath;
	}
	
	public void addLoopPath(Queue<InterfaceFlowNode> loopPath) {
		this.loopPath.add(loopPath);
	}
	
	/**
	 * Get all name of events
	 * 
	 * @return
	 */
	public Set<String> getEvents() {
		Set<String> events = new HashSet<String>();
		
		Iterator<Message> it = this.definitions.getMessages().iterator();
		Message m;
		while(it.hasNext()) {
			m = it.next();
			events.add(m.getName());
		}
		
		return events;
	}
	
	/**
	 * Get all participants in the diagram
	 * 
	 * @return
	 */
	public Set<String> getParticipants() {
		Set<String> participantNames = new HashSet<String>();
		Iterator<Participant> it = this.definitions.getChoreography().getParticipants().iterator();
		Participant p = null;
		while(it.hasNext()) {
			p = it.next();
			participantNames.add(p.getName());
		}
		return participantNames;
	}
	
	/**
	 * Get all activities in the diagram
	 * @return
	 */
	public Set<ChoreographyTask> getAllTasks() {
		return this.definitions.getChoreography().getChoreographyTasks();
	}
	
	/**
	 * Get all path that from start node to end node
	 * @return
	 */
	public Set<Queue<InterfaceFlowNode>> getValidPath() {
		if(this.validPath == null) {
			this.validPath = new HashSet<Queue<InterfaceFlowNode>>();
			
			Iterator<Queue<InterfaceFlowNode>> it = this.allPath.iterator();
			while(it.hasNext()) {
				Queue<InterfaceFlowNode> temp = it.next();
				if(Tool.isLastElementEndEvent(temp)) {
					Iterator<Queue<InterfaceFlowNode>> it_end = this.validPath.iterator();
					boolean include = false;
					while(it_end.hasNext()) {
						Queue<InterfaceFlowNode> temp_end = it_end.next();
						if(temp_end.toString().equals(temp.toString())) {
							include = true;
							break;
						}						
					}
					if(!include) {
						this.validPath.add(temp);
					}
				}
				
			}
		}
		return this.validPath;
	}
}
