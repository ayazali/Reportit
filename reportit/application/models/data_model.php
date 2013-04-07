<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of data_model
 *
 * @author Ayaz
 */
class Data_model extends CI_Model {

    //put your code here

    public function __construct() {
        parent::__construct();
    }

    function getAllZones() {


        $query = $this->db->select('*')->from('zones');
        $query = $this->db->get();

        return $query->result();
    }

    function getAllTowns() {


        $query = $this->db->select('*')->from('towns');
        $query = $this->db->get();

        return $query->result();
    }

    function getAllLocality() {


        $query = $this->db->select('*')->from('locality');
        $query = $this->db->get();

        return $query->result();
    }

    function getAllType_Crime() {


        $query = $this->db->select('*')->from('crimetype');
        $query = $this->db->get();

        return $query->result();
    }

    function report_crime($localityid, $crimeid) {

        $data = array('Locality_ID' => $localityid, 'Crime_ID' => $crimeid);
        $this->db->insert('crimereported', $data);
    }

    function allCrimes() {



        $queryString = "SELECT 
    COUNT(crimereported.CrimeReported_ID) as Count, crimetype.Crime_Type
from
    crimereported,
    crimetype
where
    crimereported.Crime_ID = crimetype.Crime_ID
group by crimereported.Crime_ID";
        
        $res=$this->db->query($queryString);
        return $res->result();
    }

}

?>
