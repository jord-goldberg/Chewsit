package ny.gelato.chewsit.flipcard.swipe;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by joshuagoldberg on 8/15/16.
 */
public class SwipeCallback extends ItemTouchHelper.Callback {

    private static final String TAG = "SwipeCallback";

    private final SwipeListener mListener;

    public SwipeCallback(SwipeListener listener) {
        mListener = listener;
    }

    public interface SwipeListener {
        void onItemDismiss(int position);
        void onItemSelect(int position);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                | ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return super.getSwipeEscapeVelocity(defaultValue -0.1f);
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        return super.getSwipeVelocityThreshold(defaultValue +0.4f);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(direction == ItemTouchHelper.END || direction == ItemTouchHelper.DOWN) {

            mListener.onItemSelect(viewHolder.getAdapterPosition());
        } else {

            mListener.onItemDismiss(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        float ratio = 255f / c.getWidth();

        // Get RecyclerView item from the ViewHolder
        View itemView = viewHolder.itemView;

        Paint p = new Paint();
        if (dX > 0) {
            p.setARGB((int) (dX*ratio), 0, 255, 0);

            // Draw Rect with varying right side, equal to displacement dX
//            c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(),
//                    (float) itemView.getRight(), (float) itemView.getBottom(), p);
        } else {
            p.setARGB((int) (Math.abs(dX)*ratio), 255, 0, 0);


            // Draw Rect with varying left side, equal to displacement dX
//            c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(),
//                    (float) itemView.getRight(), (float) itemView.getBottom(), p);
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
