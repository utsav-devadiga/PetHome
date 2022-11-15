package com.petHomeApps.petHome.Datamodels;

public class FAQ_model {
    private String faq_que;
    private String faq_ans;
    private int faq_priority;
    private boolean expanded;

    public FAQ_model(String faq_que, String faq_ans, int faq_priority) {
        this.faq_que = faq_que;
        this.faq_ans = faq_ans;
        this.faq_priority = faq_priority;
        expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getFaq_que() {
        return faq_que;
    }

    public void setFaq_que(String faq_que) {
        this.faq_que = faq_que;
    }

    public String getFaq_ans() {
        return faq_ans;
    }

    public void setFaq_ans(String faq_ans) {
        this.faq_ans = faq_ans;
    }

    public int getFaq_priority() {
        return faq_priority;
    }

    public void setFaq_priority(int faq_priority) {
        this.faq_priority = faq_priority;
    }

    public FAQ_model() {
    }
}
