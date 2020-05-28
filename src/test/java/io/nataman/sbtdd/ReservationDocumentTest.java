package io.nataman.sbtdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataMongoTest
public class ReservationDocumentTest {

  @Autowired
  private ReservationRepository reservationRepository;

  @Test
  public void persist() {
    var reservation = Reservation.builder().name("unit-test").build();
    var save = reservationRepository.save(reservation);
    StepVerifier.create(save)
        .expectNextMatches(r -> r.getId() != null && r.getName().equals("unit-test"))
        .verifyComplete();
  }
}
