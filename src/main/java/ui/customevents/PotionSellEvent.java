package ui.customevents;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import potionmod.potions.PotionOption;

public class PotionSellEvent extends AbstractImageEvent {
    public static final String ID = "EventID";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    // This text should be set up through loading an event localization json file
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static PotionOption[] potionOptions;
    private static final String NAME = eventStrings.NAME;

    public PotionSellEvent() {
        super(NAME, DESCRIPTIONS[0], "potionmod/images/events/potionvendor.png");

        potionOptions = GenerateDialogOptions();
        
        for (PotionOption option : potionOptions) {
            this.imageEventText.setDialogOption(option.optionText);
        }
    }

    @Override
    protected void buttonEffect(int arg0) {
        //treat the click arg as the index of the potion in the player's potion list
        //if the player clicks an option, sell the potion and give the player the gold
        if (arg0 < potionOptions.length - 1 && potionOptions[arg0].potion != null) {
            PotionOption potion = potionOptions[arg0];
            AbstractDungeon.topPanel.destroyPotion(potion.potion.slot);
            potion.potion = null;
            AbstractDungeon.player.gainGold(potion.sellPrice);

        }
        else {
            this.imageEventText.clearAllDialogs();
            this.imageEventText.setDialogOption("Leave");
            openMap();
        }
    }

    private PotionOption[] GenerateDialogOptions() {
        PotionOption[] result = new PotionOption[AbstractDungeon.player.potions.size() - 1];

        for(int i = 0; i < AbstractDungeon.player.potions.size() - 1; i++) {
            AbstractPotion potion = AbstractDungeon.player.potions.get(i);
            PotionOption option = new PotionOption(potion, potion.getPrice() / 4);
            option.optionText = "Sell " + potion.name + " for " + potion.getPrice() / 4 + " gold";
            result[i] = option;
        }
        result[result.length - 1].optionText = "Leave";

        return result;
    }
}

