package utcn.labs.sd.bankingservice.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.labs.sd.bankingservice.domain.data.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {

}
