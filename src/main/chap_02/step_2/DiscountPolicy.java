package chap_02.step_2;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
