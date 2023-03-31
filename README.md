# Resit_Mobile_T4


if (TextUtils.isEmpty(_name.getText().toString())) {
                    _name.setError("Please enter Name of Trip!");
                } else if (TextUtils.isEmpty(_dest.getText().toString())) {
                    _dest.setError("Please enter Destination!");
                } else if (TextUtils.isEmpty(_date.getText().toString())) {
                    _date.setError("Please enter Date of Trip!");
                } else if (TextUtils.isEmpty(_risk.getText().toString())) {
                    _risk.setError("Please Type YES/NO");
                } else {
                    getInputs();

                    //Insert Value to Database
                    myDb.addTrip(_name.getText().toString().trim(),
                            _dest.getText().toString().trim(),
                            _date.getText().toString().trim(),
                            _risk.getText().toString().trim(),
                            _desc.getText().toString().trim());
                }

