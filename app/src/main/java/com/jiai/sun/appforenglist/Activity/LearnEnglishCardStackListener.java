package com.jiai.sun.appforenglist.Activity;

import android.content.Context;
import android.widget.Toast;

import com.wenchao.cardstack.CardStack;

/**
 * Created by admin on 2016/4/18.
 */
public class LearnEnglishCardStackListener implements CardStack.CardEventListener{
    private Context context;
    LearnEnglishCardStackListener(Context context){
        this.context = context;
    }

    @Override
    public boolean swipeEnd(int section, float distance) {

        //if "return true" the dismiss animation will be triggered
        //if false, the card will move back to stack
        //distance is finger swipe distance in dp

        //the direction indicate swipe direction
        //there are four directions
        //  0  |  1
        // ----------
        //  2  |  3
        return (distance>300)?true:false;
    }

    @Override
    public boolean swipeStart(int section, float distance) {
        return false;
    }

    @Override
    public boolean swipeContinue(int section, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void discarded(int mIndex, int direction) {
        //this callback invoked when dismiss animation is finished.



    }

    @Override
    public void topCardTapped() {
        //this callback invoked when a top card is tapped by user.
    }
}
