public class receiveNine implements PinsetterReceiver{

    private Lane lane;

    public receiveNine(Lane lane){
        this.lane = lane;
    }
    @Override
    public void receive(PinsetterEvent pe) {
        if (pe.totalPinsDown() == 10) {
            this.lane.getPinsetter().resetPins();
            if(pe.getThrowNumber() == 1) {
                this.lane.setTenthFrameStrike(true);
            }
        }
        if ((pe.totalPinsDown() != 10) && (pe.getThrowNumber() == 2 && this.lane.getTenthFrameStrike() == false)) {
            this.lane.setCanThrowAgain(false);
        }

        if (pe.getThrowNumber() == 3) {
            this.lane.setCanThrowAgain(false);
        }
    }
}
