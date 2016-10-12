package ny.gelato.chewsit.flipcard.swipe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ny.gelato.chewsit.R;
import ny.gelato.chewsit.YelpHelper;
import ny.gelato.chewsit.data.DataSingleton;
import ny.gelato.chewsit.flipcard.FlipListener;

/**
 * Created by joshuagoldberg on 10/8/16.
 */

public class SwipeFragment extends Fragment implements SwipeCallback.SwipeListener {

    private static final String TAG = "SwipeFragment";

    private FlipListener mListener;

    private Unbinder mUnbinder;

    private PlateAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    ItemTouchHelper mTouchHelper;

    private InfoAdapter mInfoAdapter;
    private LinearLayoutManager mInfoLM;

    @BindView(R.id.background)
    FrameLayout mBackground;

    @BindView(R.id.info_recycler)
    RecyclerView mInfoRecycler;

    @BindView(R.id.recycler)
    RecyclerView mPlateRecycler;

    @OnClick(R.id.fab_left)
    public void dismiss() {
        YelpHelper.getInstance().getBusinesses().remove(0);
        mAdapter.notifyItemRemoved(0);
        mInfoAdapter.notifyItemRemoved(0);
    }

    @OnClick(R.id.fab_right)
    public void select() {
        zoomOut(getView());
    }

    public SwipeFragment() {
    }

    public static SwipeFragment newInstance() {
        SwipeFragment fragment = new SwipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FlipListener) {
            mListener = (FlipListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FlipListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_swipe, container, false);
        float scale = viewRoot.getContext().getResources().getDisplayMetrics().density;
        viewRoot.setCameraDistance(10000 * scale);
        mUnbinder = ButterKnife.bind(this, viewRoot);

        // Remove the ability to scroll by overriding the linearlayout manager
        mLayoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.VERTICAL, true) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mAdapter = new PlateAdapter();
        mTouchHelper = new ItemTouchHelper(new SwipeCallback(this));

        mInfoLM = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.VERTICAL, true);
        mInfoAdapter = new InfoAdapter();

        return viewRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPlateRecycler.setLayoutManager(mLayoutManager);
        mPlateRecycler.setAdapter(mAdapter);

        // Set TouchListener so that clicks through the recyclerView get to the FABs
        mPlateRecycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mBackground.dispatchTouchEvent(motionEvent);
                return false;
            }
        });

        // Attack TouchHelper to handle the swipe inputs
        mTouchHelper.attachToRecyclerView(mPlateRecycler);

        mInfoRecycler.setLayoutManager(mInfoLM);
        mInfoRecycler.setAdapter(mInfoAdapter);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                zoomIn(view);
            }
        }, 300);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onItemDismiss(int position) {
        dismiss();
    }

    @Override
    public void onItemSelect(int position) {
        select();
    }

    public void zoomIn(View view) {

        ObjectAnimator zoomInX = ObjectAnimator.ofFloat(view, "scaleX", 1.1f);
        ObjectAnimator zoomInY = ObjectAnimator.ofFloat(view, "scaleY", 1.1f);

        zoomInX.setDuration(200);
        zoomInY.setDuration(200);

        AnimatorSet zoomIn = new AnimatorSet();

        zoomIn.play(zoomInX).with(zoomInY);
        zoomIn.start();
    }

    public void zoomOut(View view) {

        ObjectAnimator zoomInX = ObjectAnimator.ofFloat(view, "scaleX", 1f);
        ObjectAnimator zoomInY = ObjectAnimator.ofFloat(view, "scaleY", 1f);

        zoomInX.setDuration(200);
        zoomInY.setDuration(200);

        AnimatorSet zoomIn = new AnimatorSet();

        zoomIn.play(zoomInX).with(zoomInY);
        zoomIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onFlip();
                    }
                }, 100);
            }
        });
        zoomIn.start();
    }
}
