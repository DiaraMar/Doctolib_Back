package com.business.app.school.DoctolibBackCodebase.infra.user;

import com.business.app.school.DoctolibBackCodebase.domain.account.Account;
import com.business.app.school.DoctolibBackCodebase.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountJPARepository extends JpaRepository<Account, Long> {

    @Override
    Optional<Account> findById(Long aLong);


    @Override
    <S extends Account> S save(S entity);

    @Query(value="select a.user_id, a.id, a.truc from doctolibdb.account_doctolib a where a.user_id= ?1",  nativeQuery = true)
    Optional<List<Account>> findAllAccountBy(@Param("id") Integer userID);
}
