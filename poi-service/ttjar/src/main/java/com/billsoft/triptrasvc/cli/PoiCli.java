package com.billsoft.triptrasvc.cli;

import com.billsoft.triptrasvc.parcel.PoiParcel;

/**
 * cli object of poi
 * 
 */
public class PoiCli extends GenericCli {

    /**
     * create given list of pois
     * 
     * @param token
     *            a string of access token
     * @param pois
     *            an array of pois
     * @return an array of integer of newly created poi ids
     */
    public int[] createPoi(String token, PoiParcel[] pois) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * delete given id of poi
     * 
     * @param token
     *            a string of access token
     * @param id
     *            an integer of poi id
     * @return an integer of delete poi number, 0 for none, 1 for 1, 1+ for poi
     *         and its child
     */
    public int delete(String token, int id) {
        // TODO Auto-generated method stub
        return -1;
    }

    /**
     * search poi by given criteria, if id is given coordinate and name will be
     * ignored
     * 
     * @param id
     *            an integer of poi id
     * @param latitude
     *            a float of poi latitude
     * @param longitude
     *            a float of poi longitude
     * @param name
     *            a string of poi name
     * @return an array of poi
     */
    public PoiParcel[] search(int id, float latitude, float longitude, String name) {
        // TODO Auto-generated method stub
        return new PoiParcel[] { new PoiParcel(0, 2), new PoiParcel(0, 3) };
    }

    public int[] updatePoi(String token, PoiParcel[] pois) {
        // TODO Auto-generated method stub
        return null;
    }

}
