package me.sa1zer.cdrsystem.commondb.repository;

import me.sa1zer.cdrsystem.commondb.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"roles", "tariff"})
    Optional<User> findUserByPhone(String phone);

    Optional<User> findUserByPhoneAndBalanceGreaterThan(String phone, double balance);

    @Query("select u from User u join fetch Operator o on o.id = u.operator.id " +
            "where o.name = :operator and u.balance > 0 and u.phone in (:phones)")
    @EntityGraph(attributePaths = {"tariff", "operator"})
    List<User> findAllWithPositiveBalance(Set<String> phones, String operator);

    @Modifying
    @Query("update User u set u.balance = u.balance + :amount where u.phone = :phone")
    void updateUserBalance(@Param("phone") String phone, @Param("amount") double amount);

    @Query("select u from User u where u.phone in (:phones)")
    List<User> findAllInSet(Set<String> phones);
}
