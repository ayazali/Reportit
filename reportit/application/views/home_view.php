<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<?php echo site_url('static/css/bootstrap.css'); ?>" rel="stylesheet">
        <style>
            .custom-container{


                background: url('<?php echo site_url('static/img/pkmap.png'); ?>') no-repeat center top;
            }

            .logo {
                background: url('<?php echo site_url('static/img/logo.png'); ?>') no-repeat;
                width: 350px;
                height: 150px;
            }

        </style>
                <link href="<?php echo site_url('static/css/datetimepicker.css'); ?>" rel="stylesheet" media="screen">
        <title><?php echo $page_title; ?></title>
    </head>
    <body class="custom-container">
        <div class="container" >
            <div class="row">
                <div class="span1">
                    <!--                    <div id="logo"></div>-->
                </div>
            </div>
            <div class="row">
                <div class="span12">
                    <div class="navbar">
                        <div class="navbar-inner">
                            <a class="brand" href="#">Report it!</a>
                            <ul class="nav">
                                <li><a href="<?php echo site_url('home'); ?>"><i class="icon-home"></i> Home</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin: 0 auto;">
                <div class="span8">
                    <div class="logo"></div>
                </div>
                <div class="span8">
                    <div class="alert fade in">
                        <button type="button" class="close" data-dismiss="alert">x</button>
                        <strong>Thanks!</strong> Your crime has been reported.
                    </div>
                    <form class="well" style="color: black;" id="addform">
                        <legend>Report Crime</legend>
                        <label>Current City Code : KHI</label>
                        <label>Zone</label>
                        <select id="zlist">
                            <option value="none" selected="selected">Select One</option>
                            <?php foreach ($zlist as $item): ?>

                                <option value="<?php echo $item->Zones_ID; ?>"><?php echo $item->Zones_Name; ?></option>

                            <?php endforeach; ?>
                        </select>
                        <label>Town</label>
                        <select id="tlist">
                            <option value="none" selected="selected">Select One</option>
                            <?php foreach ($tlist as $item): ?>

                                <option value="<?php echo $item->Town_ID; ?>"><?php echo $item->Town_Name; ?></option>

                            <?php endforeach; ?>
                        </select>
                        <div class="input-append date form_sdatetime" data-date="2013-02-21T15:25:00Z">
                            <input name="stime" size="16" type="text" value="">
                            <span class="add-on"><i class="icon-remove"></i></span>
                            <span class="add-on"><i class="icon-calendar"></i></span>
                        </div>
                        <label>Locality</label>
                        <select id="llist">
                            <option value="none" selected="selected">Select One</option>
                            <?php foreach ($llist as $item): ?>

                                <option value="<?php echo $item->Locality_ID; ?>"><?php echo $item->Locality_Name; ?></option>

                            <?php endforeach; ?>
                        </select>
                        <label>Type of Crime</label>
                        <select id="clist">
                            <option value="none" selected="selected">Select One</option>
                            <?php foreach ($clist as $item): ?>

                                <option value="<?php echo $item->Crime_ID; ?>"><?php echo $item->Crime_Type; ?></option>

                            <?php endforeach; ?>
                        </select>

                        <span class="help-block">No personal information is stored during this process.</span>
                        <a href="#" type="submit" class="btn btn-primary" id="sbutton">Report Crime</a>
                    </form>
                </div>
            </div>
        </div>
        <script src="<?php echo site_url('static/js/bootstrap.js'); ?>"></script>
        <script src="<?php echo site_url('static/js/bootstrap-transition.js'); ?>"></script>
        <script src="<?php echo site_url('static/js/jquery-latest.js'); ?>"></script>
        
        <script src="<?php echo site_url('static/js/bootstrap-datetimepicker.min.js'); ?>"></script>
        <script>
            $(document).ready(function(){
                $(".alert").hide();
                $(".form_sdatetime").datetimepicker({
                    format: "dd MM yyyy - hh:ii",
                    autoclose: true,
                    todayBtn: true,
                    todayHighlight: true,
                    startDate: "2013-02-14 10:00",
                    pickerPosition: "bottom-left",
                    minuteStep: 10
                });
                $("#sbutton").click(function(){
                    var locid=$("#llist").find("option:selected").val();
                    var cid=$("#clist").find("option:selected").val();
                    //console.log(locid+"  "+cid)
                    var url="<?php echo site_url('api/crimereport/report'); ?>?locid="+locid+"&cid="+cid;
                    var incomingData=null;
                    jQuery.ajax({
                        async: true,
                        type: "GET",
                        url: url,
                        data: null,
                        success: function(){ 
                            //alert('Your crime has been reported !');
                            $(".alert").alert()
                            $(".alert").show()
                        },
                        dataType: 'script'
                    });
                });
            });
            
        </script>
    </body>
</html>