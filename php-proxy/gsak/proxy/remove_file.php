<?php 
if(isset($_GET['file'])){
    $file = '../uploads/' . $_POST['file'];
    if(file_exists($file)){
        unlink($file);
    }
}
?>