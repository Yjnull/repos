package com.yjnull.latte.pos.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.joanzapata.iconify.widget.IconTextView;
import com.yjnull.latte.pos.R;
import com.yjnull.latte.pos.R2;
import com.yjnull.latte.ui.recycler.MultipleItemEntity;
import com.yjnull.latte_core.delegates.bottom.BottomItemDelegate;
import com.yjnull.latte_core.net.RestClient;
import com.yjnull.latte_core.net.callback.ISuccess;
import com.yjnull.latte_core.util.log.LatteLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yangya on 2018/7/14.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener{

    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;       //当前选中的 Item
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @SuppressWarnings("ConstantConditions")
    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新 Recyclerview 的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> datas =  mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : datas) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }

        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {

                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        checkItemCount();

    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        mTvTotalPrice.setText(String.valueOf(0.00));
        checkItemCount();
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        createOrder();
    }

    private void createOrder() {
        final String orderUrl = "你的生成订单的API";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        //加入你的参数
        orderParams.put("userid", "");
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        orderParams.put("isanonymous", true);
        orderParams.put("followeduser", 0);
        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        LatteLogger.d("ORDER", response);
                        final int orderId = JSON.parseObject(response).getInteger("result");

                    }
                })
                .build()
                .post();
    }

    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            if (mStubNoItem.getParent() != null) {
                @SuppressLint("RestrictedApi") final View stubView = mStubNoItem.inflate();
                final AppCompatTextView tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy);
                tvToBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                    }
                });
                mRecyclerView.setVisibility(View.GONE);
            }
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_card")
                .success(this)
                .loader(getContext())
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setCartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }
}
