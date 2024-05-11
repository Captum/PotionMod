package ui.customevents;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

public class PotionSellEvent extends AbstractImageEvent {
    public static final String ID = "My First Event";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    // This text should be set up through loading an event localization json file
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;

    public PotionSellEvent() {
        super(NAME, DESCRIPTIONS[0], "images/events/filler.png");

        for (String option : OPTIONS) {
            this.imageEventText.setDialogOption(option);
        }
    }

    @Override
    protected void buttonEffect(int arg0) {
        if (arg0 == 0) {
            this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
            this.imageEventText.updateDialogOption(0, OPTIONS[1]);
            this.imageEventText.clearRemainingOptions();
        } else if (arg0 == 1) {
            this.imageEventText.updateBodyText(DESCRIPTIONS[0]);
            this.imageEventText.updateDialogOption(0, OPTIONS[0]);
            this.imageEventText.clearRemainingOptions();
        }
    }

}
