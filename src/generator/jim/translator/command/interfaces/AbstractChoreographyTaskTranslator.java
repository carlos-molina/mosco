package generator.jim.translator.command.interfaces;

import generator.jim.entites.InlineChannel;
import simulator.jim.choreographies.entites.ChoreographyTask;
import simulator.jim.choreographies.entites.Participant;

public abstract class AbstractChoreographyTaskTranslator implements InterfaceTranslator {

	private ChoreographyTask choreographyTask;
	protected boolean passed = false;
	
	protected AbstractChoreographyTaskTranslator() {}
	
	protected AbstractChoreographyTaskTranslator(ChoreographyTask choreographyTask) {
		this.choreographyTask = choreographyTask;
	}

	
	public void setChoreographyTask(ChoreographyTask choreographyTask) {
		this.choreographyTask = choreographyTask;
	}
	
	protected ChoreographyTask getChoreographyTask() {
		if(this.choreographyTask == null) {
			throw new RuntimeException("choreographyTask has not been initailized.");
		}
		return this.choreographyTask;
	}

	protected InlineChannel getChannel() {
		String from = this.choreographyTask.getInitiatingParticipantRef().getName();
		String to = ((Participant)(this.choreographyTask.getParticipants().toArray()[0])).getName();
		return InlineChannel.getChannel(from, to);
	}
}
