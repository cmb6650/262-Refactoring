public class elseReceiver implements PinsetterReceiver{
    private Lane lane;

    public elseReceiver(Lane lane){
        this.lane = lane;
    }
    @Override
    public void receive(PinsetterEvent pe) {
        if (pe.pinsDownOnThisThrow() == 10) {		// threw a strike
            this.lane.setCanThrowAgain(false);
            //publish( lanePublish() );
        } else if (pe.getThrowNumber() == 2) {
            this.lane.setCanThrowAgain(false);
            //publish( lanePublish() );
        } else if (pe.getThrowNumber() == 3)
            System.out.println("I'm here...");
    }
}
