package hu.ait.android.weatherapp.touch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by sarahjin on 11/22/17.
 */

public class CityItemTouchHelperCallback extends
        ItemTouchHelper.Callback {

    private CityTouchHelperAdapter cityTouchHelperAdapter;

    public CityItemTouchHelperCallback(CityTouchHelperAdapter cityTouchHelperAdapter) {
        this.cityTouchHelperAdapter = cityTouchHelperAdapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    public boolean isItemViewSwiperEnabled(){
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        cityTouchHelperAdapter.onItemMove(
                viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        cityTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
