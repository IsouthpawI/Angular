package com.viktoriia.mobiles.dao;

import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.PersonNumber;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Transactional
@Repository
public class PersonNumberDAO implements IPersonNumberDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createPersonNumber(PersonNumber personNumber, Mobile mobile) {
        personNumber.setMobile(mobile);
        System.out.println(personNumber.getMobile().toString());

        //String hql = "INSERT INTO PersonNumber";
       // entityManager.createQuery(hql);

        entityManager.persist(personNumber);
    }

    @Override
    public boolean personNumberExists(long phoneNumberM) {
        String hql = "FROM PersonNumber as pn WHERE  pn.mobile.phoneNumberM = ?";
        int count = entityManager.createQuery(hql)
                .setParameter(0, phoneNumberM).getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    public List<PersonNumber> findNumbersByFirstNameOrLastName(String firstName, String lastName) {
        boolean isFirstNameExample = false;
        boolean isLastNameExample = false;
        System.out.println("CO ZA NAFIG");

        if (firstName.equals("example")){
            isFirstNameExample = true;
        }
        if (lastName.equals("example")){
            isLastNameExample = true;
        }

        if (!isFirstNameExample){
            if (!isLastNameExample){
                String hql = "FROM PersonNumber mob WHERE mob.firstName = ? and mob.lastName = ?";
//
//                if (entityManager.createQuery(hql).setParameter(0, firstName)
//                        .setParameter(1,lastName).getResultList().size() == 0){
//                    System.out.println("ON PUSTOJ");
//                    return new ArrayList<PersonNumber>();
//                }
//                else
                    return entityManager.createQuery(hql).setParameter(0, firstName)
                        .setParameter(1,lastName).getResultList();
            }
            else {
                System.out.println("CO ZA NAFIG 2");
                String hql = "FROM PersonNumber mob WHERE mob.firstName = ?";
//
//                if (entityManager.createQuery(hql).setParameter(0, firstName).getResultList().size() == 0){
//                    return new ArrayList<PersonNumber>();
//                }
//                else
                    return entityManager.createQuery(hql).setParameter(0, firstName).getResultList();
            }
        }else{
            if (!isLastNameExample){
                String hql = "FROM PersonNumber mob WHERE mob.lastName = ?";
//                if (entityManager.createQuery(hql).setParameter(0, lastName).getResultList().size() == 0){
//                    return new ArrayList<PersonNumber>();
//                }
//                else
                    return entityManager.createQuery(hql).setParameter(0,lastName).getResultList();
            }
        }
        //System.out.println("RETURN NULL");
        return new ArrayList<PersonNumber>();
    }



    //NOVA
    @Override
    public List<PersonNumber> findNumbersByFirstNameOrLastName2(String firstName, String lastName) {
        int countOfParam = 0;
        Map<Integer, String> param = new HashMap<Integer, String>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("FROM PersonNumber mob");
        Query query = entityManager.createQuery(queryBuilder.toString());
        boolean isFirstNameExample = false;
        boolean isLastNameExample = false;
        if (firstName != null){
            queryBuilder.append(" WHERE mob.firstName = ?");
            param.put(countOfParam, firstName);
            countOfParam++;
        }

        if (lastName != null) {
            if (queryBuilder.toString().contains("?")) {
                queryBuilder.append(" and mob.lastName = ?");
                param.put(countOfParam, lastName);
                countOfParam++;
            }
            else {
                System.out.println("RABOTAET");
                queryBuilder.append(" WHERE mob.lastName = ?");
                param.put(countOfParam, lastName);
                countOfParam++;
            }
        }

            query = entityManager.createQuery(queryBuilder.toString());
            for (Map.Entry<Integer, String> pair: param.entrySet()){
                query.setParameter(pair.getKey(), pair.getValue());
            }

            return query.getResultList();
    }



    @Override
    public boolean deletePersonNumber(Mobile mobileById) {
        String hql = "FROM PersonNumber as atcl where atcl.mobile.phoneNumberM = ?";
        List<PersonNumber> personNumbers = new ArrayList<PersonNumber>();
        personNumbers = (List<PersonNumber>) entityManager.createQuery(hql).setParameter(0, mobileById.getPhoneNumberM()).getResultList();
        PersonNumber person = new PersonNumber();
        int count =  entityManager.createQuery(hql).setParameter(0, mobileById.getPhoneNumberM()).getResultList().size();
        if (count == 0 ){
            return false;
        }

        if (!personNumbers.isEmpty()){
            person = personNumbers.get(0);
            entityManager.remove(person);
            return true;
        }
        else return false;

    }

    @Override
    public PersonNumber getPersonNumberByMobile(Mobile mobileById) {
        String hql = "FROM PersonNumber as atcl where atcl.mobile.phoneNumberM = ?";
        List<PersonNumber> personNumbers = new ArrayList<PersonNumber>();
        personNumbers = (List<PersonNumber>) entityManager.createQuery(hql).setParameter(0, mobileById.getPhoneNumberM()).getResultList();
        int count =  entityManager.createQuery(hql).setParameter(0, mobileById.getPhoneNumberM()).getResultList().size();
        if (count == 0 ){
            return null;
        }
        PersonNumber person = new PersonNumber();
        System.out.println("NULL: " + personNumbers.get(0));
        if (!personNumbers.isEmpty()){
            person = personNumbers.get(0);
            return entityManager.find(PersonNumber.class, person.getId());
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteAllPersons() {
        List<PersonNumber> personNumbers = new ArrayList<PersonNumber>();
        personNumbers = getAllPersonNumbers();
        if (personNumbers.isEmpty()){
            return false;
        }
        else {
            for (int i = 0; i < personNumbers.size(); i++) {
                // entityManager.remove((mobiles.get(i)).getPhoneNumberM());

                entityManager.remove(personNumbers.get(i));
            }
            return true;
        }
    }

    @Override
    public void updatePersonNumber(Mobile mobile) {
        System.out.println("UPDATE SAVE");
        PersonNumber pn = getPersonNumberByMobile(mobile);
        if (pn == null){
            System.out.println("NUUUUUULLL");
           PersonNumber pnNew = new PersonNumber();
            pnNew.setFirstName(mobile.getFirstName());
            pnNew.setLastName(mobile.getLastName());
            pnNew.setMobile(mobile);
            createPersonNumber(pnNew, mobile);
        }
        else {
            pn.setFirstName(mobile.getFirstName());
            pn.setLastName(mobile.getLastName());
        }
        entityManager.flush();
    }

    private List<PersonNumber> getAllPersonNumbers() {
        String hql = "FROM PersonNumber as atcl ORDER BY atcl.firstName DESC";
        return (List<PersonNumber>) entityManager.createQuery(hql).getResultList();

    }
}
