package test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.blacklist.BlacklistNumber;
import com.example.blacklist.BlacklistNumberDAO;

import java.util.List;

/**
 * BlacklistNumber DAO Test
 * Created by albertli on 1/12/2016.
 */
public class BlacklistNumberDAOTest extends AndroidTestCase {
    public void testAdd(){
        BlacklistNumberDAO dao = new BlacklistNumberDAO(getContext());
        dao.add(new BlacklistNumber(-1, "1234"));
        dao.add(new BlacklistNumber(-1, "12345"));
        dao.add(new BlacklistNumber(-1, "123456"));
    }

    public void testGetAll(){
        BlacklistNumberDAO dao = new BlacklistNumberDAO(getContext());
        List<BlacklistNumber> list = dao.getAll();
        Log.i("TAG",list.toString());

    }

    public void testDelete(){
        BlacklistNumberDAO dao = new BlacklistNumberDAO(getContext());
        dao.delete(1);
    }

    public void testUpdate(){
        BlacklistNumberDAO dao = new BlacklistNumberDAO(getContext());
        dao.update(new BlacklistNumber(2,"4321"));
    }
}
