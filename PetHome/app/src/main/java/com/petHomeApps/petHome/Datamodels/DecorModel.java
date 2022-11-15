package com.petHomeApps.petHome.Datamodels;

public class DecorModel {
    String user_id;
    String order_id;
    String plan_name;
    boolean payment_status;
    String payment_id;
    long dop;

    public DecorModel(String user_id, String order_id, String plan_name, boolean payment_status, String payment_id, long dop) {
        this.user_id = user_id;
        this.order_id = order_id;
        this.plan_name = plan_name;
        this.payment_status = payment_status;
        this.payment_id = payment_id;
        this.dop = dop;
    }

    public DecorModel() {
    }



    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public boolean isPayment_status() {
        return payment_status;
    }

    public void setPayment_status(boolean payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public long getDop() {
        return dop;
    }

    public void setDop(long dop) {
        this.dop = dop;
    }
}
