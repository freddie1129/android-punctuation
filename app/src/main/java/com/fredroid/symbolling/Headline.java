package com.fredroid.symbolling;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Headline extends ListFragment {

    public static int mMode;

    public Headline() {
        // Required empty public constructor
    }

    public void setMode(int n) {
        mMode = n;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final SymbolAdapter adapter = new SymbolAdapter(getActivity(), getSymbolList(mMode));
        setListAdapter(adapter);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv = getListView();
        lv.setDividerHeight(0);


    }

    public ArrayList<Symbol> getSymbolList(int nMode) {
        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
        Symbol s1;
        String[][] data = (nMode == FirstActivity.FUNC_SYMBOL) ? Punctuations.data : Punctuations.datagreek;
        for (int i = 0; i < data.length; i++)
            symbolList.add(new Symbol(data[i][0], data[i][1]));
        return symbolList;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Symbol s = (Symbol) l.getItemAtPosition(position);
        String strAudioPath = s.strspell.replace(" ", "_");
        int drawableResourceId = this.getResources().getIdentifier(strAudioPath, "raw", getActivity().getPackageName());
        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), drawableResourceId);
        mediaPlayer.start();
    }


}
