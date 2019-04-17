<!DOCTYPE html>
<html lang="en">

<head>
    <title>Page Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles.css">

</head>
<body>
      
<?php include 'nav.php'; ?>
  
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="index.php">Αρχική</a></li>
    <li class="breadcrumb-item active" aria-current="page">Επικοινωνία</li>
  </ol>
</nav>

<center><h1>Επικοινωνία</h1> </center> 

  <form id="hide">

    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Όνομα:</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="name">
        </div>
    </div>

    <div class="form-group row">
        <label for="surname" class="col-sm-2 col-form-label">Επίθετο:</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="surname">
        </div>
    </div>
      
    <div class="form-group row">
        <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="staticEmail">
        </div>
      </div>

      <div class="form-group row">
        <label for="phone" class="col-sm-2 col-form-label">Τηλέφωνο</label>
        <div class="col-sm-10">
          <input type="text" class="form-control-plaintext" id="phone">
        </div>
      </div>

      <div class="row">
          <div class="col-md-12">
              <div class="form-group">
                  <label for="form_message">Μήνυμα * </label>
                  <textarea id="form_message" name="message" class="form-control col-10" placeholder="Εισαγωγή μηνύματος *" rows="4" required="required" data-error="Δεν γράψατε κάποιο μήνυμα"></textarea>
                  <div class="help-block with-errors"></div>
              </div>
          </div>
          <div class="col-md-12">
              <input type="submit" class="btn btn-success btn-send" value="Αποστολή μηνύματος">
          </div>
      </div>

<?php include 'footer.php'; ?>

</body>
</html>