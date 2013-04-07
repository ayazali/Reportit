<?php

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Example
 *
 * This is an example of a few basic user interaction methods you could use
 * all done with a hardcoded array.
 *
 * @package		CodeIgniter
 * @subpackage	Rest Server
 * @category	Controller
 * @author		Phil Sturgeon
 * @link		http://philsturgeon.co.uk/code/
 */
// This can be removed if you use __autoload() in config.php OR use Modular Extensions
require APPPATH . '/libraries/REST_Controller.php';

class Crimereport extends REST_Controller {

    //put your code here

    public function __construct() {
        parent::__construct();
        $this->load->model('Data_model');
    }

    function info_get() {
        
    }

    function report_get() {

        $message = array('zoneid' => $this->get('zid'), 'townid' => $this->get('tid'), 'localityid' => $this->get('locid'), 'ctypeid' => $this->get('cid'));
        $this->Data_model->report_crime($message['localityid'],$message['ctypeid']);
        $this->response(array("Response"=>'Success'));
        
    }
    
    function allCrimes_get(){
        
        
        
        $res=$this->Data_model->allCrimes();
        $this->response($res);
        
    }

}

?>
