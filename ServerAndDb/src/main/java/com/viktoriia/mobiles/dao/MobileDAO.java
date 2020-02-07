package com.viktoriia.mobiles.dao;


import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.PersonNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


//TODO: update a delete with personDAO
@Transactional
@Repository
public class MobileDAO implements IMobileDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Mobile getMobileById(long phoneNumberM) {
        return entityManager.find(Mobile.class, phoneNumberM);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Mobile> getAllMobiles() {
        String hql = "FROM Mobile as atcl ORDER BY atcl.firstName DESC";
        return (List<Mobile>) entityManager.createQuery(hql).getResultList();
    }


    @Override
    public void createMobile(Mobile mobile) {
        entityManager.persist(mobile);
    }

    @Override
    public void updateMobile(Mobile mobile) {
        Mobile mob = getMobileById(mobile.getPhoneNumberM());
        mob.setFirstName(mob.getFirstName());
        mob.setLastName(mob.getLastName());
        mob.setAddress(mob.getAddress());
        mob.setPhoneNumberH(mob.getPhoneNumberM());
        mob.setAdded(mob.getAdded());
        entityManager.flush();
    }

    @Override
    public void updateMobile(long phoneNumberM, Mobile mobile) {
        System.out.println("UPDATE SAVE");
        Mobile mob = getMobileById(phoneNumberM);
        mob.setFirstName(mobile.getFirstName());
        mob.setLastName(mobile.getLastName());
        mob.setAddress(mobile.getAddress());
        mob.setPhoneNumberH(mobile.getPhoneNumberH());
        mob.setAdded(mobile.getAdded());
        entityManager.flush();
    }

    @Override
    public void deleteMobile(long phoneNumberM) {
        entityManager.remove(getMobileById(phoneNumberM));
    }

    @Override
    public boolean mobileExists(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH, Date added) {
        String hql = "FROM Mobile as mob WHERE  mob.phoneNumberM = ?";
        int countM = entityManager.createQuery(hql).setParameter(0, phoneNumberM).getResultList().size();
        return countM > 0 ? true : false;



//        String hql = "FROM Mobile as mob WHERE mob.firstName = ? and mob.lastName = ? and mob.address = ? and mob.phoneNumberM = ? and mob.phoneNumberH = ? and mob.added = ?";
//        int count = entityManager.createQuery(hql).setParameter(0, firstName)
//                .setParameter(1, lastName)
//                .setParameter(2, address)
//                .setParameter(3, phoneNumberM)
//                .setParameter(4, phoneNumberH)
//                .setParameter(5, added).getResultList().size();
//        return count > 0 ? true : false;
    }


    @Override
    public List<Mobile> findByParameters(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH) {
        boolean isFirstNameExample = false;
        boolean isLastNameExample = false;
        boolean isAddressExample = false;
        boolean isPhoneNumberMNull = false;
        boolean isPhoneNumberHNull= false;

        List<Mobile> mobiles = new ArrayList<Mobile>();

        if (firstName.equals("example")){
           isFirstNameExample = true;

            // String hql = "FROM Mobile as mob WHERE mob.firstName = ? ";
           // mobiles = (List<Mobile>) entityManager.createQuery(hql).setParameter(0, firstName).getResultList();
        }
        if (lastName.equals("example")){
            isLastNameExample = true;
            //lastName = "";

        }
        if (address.equals("example")){
            isAddressExample = true;
          //  address = "";
        }
        if (phoneNumberM == 0){
            isPhoneNumberMNull = true;
        }
        if (phoneNumberH == 0){
            isPhoneNumberHNull = true;
        }


        if (!isFirstNameExample){
            if (!isLastNameExample){
                if (!isPhoneNumberMNull){
                    String hql = "FROM Mobile as mob WHERE mob.firstName = ? and mob.lastName = ?  and mob.phoneNumberM = ?";
                    return entityManager.createQuery(hql).setParameter(0, firstName)
                            .setParameter(1, lastName)
                            .setParameter(2, phoneNumberM).getResultList();
                }
                else {
                    String hql = "FROM Mobile as mob WHERE mob.firstName = ? and mob.lastName = ?";
                    return entityManager.createQuery(hql).setParameter(0, firstName)
                            .setParameter(1, lastName).getResultList();
                }

            }else {
                if (!isPhoneNumberMNull){
                    String hql = "FROM Mobile as mob WHERE mob.firstName = ? and mob.phoneNumberM = ?";
                    return entityManager.createQuery(hql).setParameter(0, firstName)
                            .setParameter(1, phoneNumberM).getResultList();
                }
                else {
                    String hql = "FROM Mobile as mob WHERE mob.firstName = ?";
                    return entityManager.createQuery(hql).setParameter(0, firstName).getResultList();
                }
            }
        }
        else {
            if (!isLastNameExample){
                if (!isPhoneNumberMNull){
                    String hql = "FROM Mobile as mob WHERE mob.lastName = ?  and mob.phoneNumberM = ?";
                    return entityManager.createQuery(hql).setParameter(0, lastName)
                            .setParameter(1, phoneNumberM).getResultList();
                }
                else {
                    String hql = "FROM Mobile as mob WHERE mob.lastName = ?";
                    return entityManager.createQuery(hql).setParameter(0, lastName).getResultList();
                }
            }
            else {
                if (!isPhoneNumberMNull){
                    String hql = "FROM Mobile as mob WHERE mob.phoneNumberM = ?";
                    return entityManager.createQuery(hql).setParameter(0, phoneNumberM).getResultList();
                }
            }
        }
        System.out.println("RETURN NULL");
        return new ArrayList<Mobile>();
    }


    @Override
    public List<Mobile> findByParameters2(String firstName, String lastName, String address, String phoneNumberM, String phoneNumberH) {
        System.out.println("PhonenumberM: " + phoneNumberM);
        int countOfParam = 0;
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("FROM Mobile as mob");
        Query query = entityManager.createQuery(queryBuilder.toString());
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

        if (address != null) {
            if (queryBuilder.toString().contains("?")) {
                queryBuilder.append(" and mob.address = ?");
                param.put(countOfParam, address);
                countOfParam++;
            }
            else {
                System.out.println("RABOTAET");
                queryBuilder.append(" WHERE mob.address = ?");
                param.put(countOfParam, address);
                countOfParam++;
            }
        }

        if (phoneNumberM != null) {
            if (queryBuilder.toString().contains("?")) {
                queryBuilder.append(" and mob.phoneNumberM = ?");
                param.put(countOfParam, Long.parseLong(phoneNumberM));
                countOfParam++;
            }
            else {
                System.out.println("RABOTAET");
                queryBuilder.append(" WHERE mob.phoneNumberM = ?");
                param.put(countOfParam, Long.parseLong(phoneNumberM));
                countOfParam++;
            }
        }
        if (phoneNumberH != null) {
            if (queryBuilder.toString().contains("?")) {
                queryBuilder.append(" and mob.phoneNumberH = ?");
                param.put(countOfParam, Long.parseLong(phoneNumberH));
                countOfParam++;
            }
            else {
                System.out.println("RABOTAET");
                queryBuilder.append(" WHERE mob.phoneNumberH = ?");
                param.put(countOfParam, Long.parseLong(phoneNumberH));
                countOfParam++;
            }
        }
        query = entityManager.createQuery(queryBuilder.toString());
        for (Map.Entry<Integer, Object> pair: param.entrySet()){
            query.setParameter(pair.getKey(), pair.getValue());
        }
        return query.getResultList();
    }

    public int getCountOfMobiles(){
        String hql = "FROM Mobile";
        int count = entityManager.createQuery(hql).getResultList().size();
        System.out.println("COUNT: "+ count);
        return count;
    }

    public List<Mobile> getMobilesList(long numberOfShowedMobiles, long firstIndex, long finishIndex){
        System.out.println("GET ALL MOBILES");
        List<Mobile> mobiles = new ArrayList<Mobile>();
        List<Mobile> mobiles1 = new ArrayList<Mobile>();
        mobiles = getAllMobiles();
        if (finishIndex < mobiles.size()) {
            for (; firstIndex <= finishIndex; firstIndex++) {
                System.out.println("OTSCHET POSEL");
                mobiles1.add(mobiles.get(Integer.parseInt(Long.toString(firstIndex))));
            }
        } else {
            for (; firstIndex < mobiles.size(); firstIndex++) {
                System.out.println("OTSCHET POSEL INDEX FINISH BOLSE CEM SIZE");
                mobiles1.add(mobiles.get(Integer.parseInt(Long.toString(firstIndex))));
            }
        }
        return mobiles1;

    }

//    public List<Mobile> getMobilesList2(long numberOfShowedMobiles, long firstIndex, long finishIndex){
//        System.out.println("GET ALL MOBILES");
//        List<Mobile> mobiles = new ArrayList<Mobile>();
//        List<Mobile> mobiles1 = new ArrayList<Mobile>();
//        mobiles = getAllMobiles();
//        if (finishIndex < mobiles.size()) {
//            for (; firstIndex <= finishIndex; firstIndex++) {
//                System.out.println("OTSCHET POSEL");
//                mobiles1.add(mobiles.get(Integer.parseInt(Long.toString(firstIndex))));
//            }
//        } else {
//            for (; firstIndex < mobiles.size(); firstIndex++) {
//                System.out.println("OTSCHET POSEL INDEX FINISH BOLSE CEM SIZE");
//                mobiles1.add(mobiles.get(Integer.parseInt(Long.toString(firstIndex))));
//            }
//        }
//        return mobiles1;
//
//    }

    @Override
    public boolean deleteAllMobile() {
        List<Mobile> mobiles = new ArrayList<Mobile>();
        mobiles = getAllMobiles();
        if (mobiles.isEmpty()){
            return false;
        }
        else {
            for (int i = 0; i < mobiles.size(); i++) {
                // entityManager.remove((mobiles.get(i)).getPhoneNumberM());

                entityManager.remove(getMobileById(mobiles.get(i).getPhoneNumberM()));
            }
            return true;
        }
    }


}
