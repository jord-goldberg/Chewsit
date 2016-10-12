package ny.gelato.chewsit.flipcard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ny.gelato.chewsit.flipcard.detail.DetailFragment;
import ny.gelato.chewsit.R;
import ny.gelato.chewsit.flipcard.swipe.SwipeFragment;

/**
 * Created by joshuagoldberg on 9/9/16.
 */
public class FlipCardFragment extends Fragment {

    private boolean mShowingBack = false;

    private SwipeFragment mSwipeFragment;

    public static FlipCardFragment newInstance() {
        FlipCardFragment fragment = new FlipCardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeFragment = SwipeFragment.newInstance();
        if (getArguments() != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_flipcard, container, false);
        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .add(R.id.flipcard_container, mSwipeFragment)
                    .commit();
        }

    }

    public void flip() {

        if (mShowingBack) {
            getChildFragmentManager().popBackStack();
            mShowingBack = false;
            return;
        }

        // Flip to the back.
        getChildFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out)
                .replace(R.id.flipcard_container, DetailFragment.newInstance())
                .addToBackStack(null)
                .commit();

        mShowingBack = true;
    }

    public boolean isShowingBack() {
        return mShowingBack;
    }
}
