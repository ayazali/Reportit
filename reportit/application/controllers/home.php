<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of home
 *
 * @author Ayaz
 */
class Home extends CI_Controller {
    //put your code here
    
    public function __construct() {
        parent::__construct();
        $this->load->model('Data_model');
    }
    
    function index(){
        
        $zones=  $this->Data_model->getAllZones();
        $towns=  $this->Data_model->getAllTowns();
        $locality = $this->Data_model->getAllLocality();
        $crime=  $this->Data_model->getAllType_Crime();
        
        $data=array('page_title'=>'Pick','zlist'=>$zones,'tlist'=>$towns,'llist'=>$locality,'clist'=>$crime);
        $this->load->view('home_view',$data);
        
    }
}

?>
