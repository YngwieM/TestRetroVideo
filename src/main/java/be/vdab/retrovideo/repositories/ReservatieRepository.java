package be.vdab.retrovideo.repositories;

import be.vdab.retrovideo.domain.Reservatie;

public interface ReservatieRepository {
    long create(Reservatie reservatie);
}
