package generator.jim.test;

import ncl.b1037041.bpmn.choreographies.*;

import generator.jim.convert.*;
import generator.jim.sequence.creator.*;
import generator.jim.tools.Configuration;
import generator.jim.tools.FileGenerator;

public class AutomaticGenerator {

	public static final String ENABLE = "enable";

	public static void main(String[] args) throws Exception {

		/*String BPMNName = Configuration.getInstance().getValue("BPMNName");
		Context context = Context.init("/BPMN/" + BPMNName);
		String currentTime = System.currentTimeMillis() + "";

		generate(BPMNName, context, currentTime);

		// The loop path
		System.out.println("LoopPath: " + context.getLoopPath().size());
		Iterator<Queue<InterfaceFlowNode>> it_loop = context.getLoopPath().iterator();
		while (it_loop.hasNext()) {
			System.out.println(it_loop.next());
		}

		// The valid path
		System.out.println("ValidPath: " + context.getValidPath().size());
		Iterator<Queue<InterfaceFlowNode>> it_valid = context.getValidPath().iterator();
		while (it_valid.hasNext()) {
			System.out.println(it_valid.next());
		}*/
	}

	private static void generate(String BPMNName, Context context,
			String currentTime) {
		if (ENABLE.equals(Configuration.getInstance().getValue("generation.model"))) {
			InterfaceConvertor convertor = AbstractConvertor
					.getConvertor(context);
			String promelaContent = convertor.convert();
			
			/*if (ENABLE.equals(Configuration.getInstance().getValue("generation.LTL"))) {
				InterfaceLTLCreator ltlCreator = new LTLCreator();
				String ltlContent = ltlCreator.create();
				promelaContent = promelaContent + ltlContent;
			}*/
			
			System.out.println(promelaContent);
			FileGenerator.generatorFile(BPMNName + "_model", promelaContent,
					currentTime);
		}

		if (ENABLE.equals(Configuration.getInstance().getValue("generation.sequences"))) {
			InterfaceSequenceCreator creator = AbstractSequenceCreator
					.getSequenceCreator(context, Configuration.getInstance()
							.getValue("generation.sequences.format"));
			String sequenceContent = creator.create();
			System.out.println(sequenceContent);
			FileGenerator.generatorFile(BPMNName + "_seq", sequenceContent,
					currentTime);
		}
	}
}