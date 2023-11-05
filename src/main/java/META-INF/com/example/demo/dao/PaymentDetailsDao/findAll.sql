SELECT paymentmethoddetails.id,
	paymentmethoddetails.paymethodid,
	paymentmethoddetails.staffid,
	paymentmethoddetails.billid,
	paymentmethoddetails.paystatus,
	staff.name as staffName,
	paymentmethod.name as paymentmethodName
FROM paymentmethoddetails
JOIN staff on staff.id = paymentmethoddetails.staffid
JOIN Paymentmethod on paymentmethod.id = paymentmethoddetails.paymethodid