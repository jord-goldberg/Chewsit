package ny.gelato.chewsit.flipcard.detail;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ny.gelato.chewsit.R;
import ny.gelato.chewsit.flipcard.FlipListener;

/**
 * Created by joshuagoldberg on 10/9/16.
 */

public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";

    private FlipListener mListener;

    private Unbinder mUnbinder;

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
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
        View viewRoot = inflater.inflate(R.layout.fragment_detail, container, false);
        float scale = viewRoot.getContext().getResources().getDisplayMetrics().density;
        viewRoot.setCameraDistance(10000 * scale);
        mUnbinder = ButterKnife.bind(this, viewRoot);

        return viewRoot;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
