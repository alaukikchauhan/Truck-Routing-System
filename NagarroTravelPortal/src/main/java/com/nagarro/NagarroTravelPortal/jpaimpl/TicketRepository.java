package com.nagarro.NagarroTravelPortal.jpaimpl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.NagarroTravelPortal.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>
{

}
