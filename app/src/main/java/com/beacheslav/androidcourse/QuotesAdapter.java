package com.beacheslav.androidcourse;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beacheslav.androidcourse.POJO.Quote;
import com.beacheslav.androidcourse.POJO.TrumpQuotes;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {

    private static final String TAG = "QuotesAdapter";

    private List<Quote> mQuotes;

    public void setQuotes(TrumpQuotes trumpQuotes) {
        mQuotes = trumpQuotes.getEmbedded().getQuotes();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quote quote = mQuotes.get(position);
        holder.mTextQuote.setText(quote.getValue());
    }

    @Override
    public int getItemCount() {
        if (mQuotes == null)
            return 0;
        return mQuotes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextQuote;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextQuote = (TextView) itemView.findViewById(R.id.text_view_quote);
        }
    }
}
