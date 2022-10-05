package chap_02.step_1;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreening;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreening) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreening = whenScreening;
    }

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }
    public LocalDateTime getStartTime() {
        return this.whenScreening;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }
}
