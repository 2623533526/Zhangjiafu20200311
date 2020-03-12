package com.bawei.zhangjiafu20200311;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: 张家辅
 * @date: 2020/03/11
 */
public class MyFragment extends Fragment {

    private Retrofit retrofit;
    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.myfragment, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv = getActivity().findViewById(R.id.rv);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,RecyclerView.VERTICAL));
        final String status = getArguments().getString("status");
        ArrayMap<String, String> headermap = new ArrayMap<>();
        headermap.put("userId","27842");
        headermap.put("sessionId","158391593760627842");
        ArrayMap<String, String> querymap = new ArrayMap<>();
        querymap.put("status",status);
        querymap.put("page","1");
        querymap.put("count","5");
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .build();
        retrofit = new Retrofit.Builder()
                .client(okhttp)
                .baseUrl(Api.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(MyService.class)
                .get(headermap,querymap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Entity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Entity entity) {
                        List<Entity.OrderListBean> orderList = entity.getOrderList();
                        Adapter adapter = new Adapter(getActivity(), orderList);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static MyFragment getInstance(int status) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("status",status+"");
        myFragment.setArguments(bundle);
        return myFragment;
    }
}
