insert into JOB (name) values ('DRIVER')
insert into JOB (name) values ('DOCTOR')
insert into JOB (name) values ('MEDIC')

insert into EMPLOYEE (name,job_id,night,monthly_hours) values ('Vizi Marci', select id from JOB where name='DRIVER', false, 160)
insert into EMPLOYEE (name,job_id,night,monthly_hours) values ('Hegyi Józsi', select id from JOB where name='DOCTOR', false, 160)
insert into EMPLOYEE (name,job_id,night,monthly_hours) values ('Kuti Béla', select id from JOB where name='MEDIC', false, 160)
insert into EMPLOYEE (name,job_id,night,monthly_hours) values ('Tanyasi Zoli', select id from JOB where name='DRIVER', false, 160)
insert into EMPLOYEE (name,job_id,night,monthly_hours) values ('Városi Matyi', select id from JOB where name='DOCTOR', false, 160)
insert into EMPLOYEE (name,job_id,night,monthly_hours) values ('Földi Elek', select id from JOB where name='MEDIC', false, 160)

insert into CAR_TYPE (name) values ('KIM')
insert into CAR_TYPE (name) values ('ROKO')
insert into CAR_TYPE (name) values ('ESETKOCSI')
insert into CAR_TYPE (name) values ('ONE')

insert into CAR (plate_number,car_type_id) values ('HHH-001', select id from CAR_TYPE where name='KIM')
insert into CAR (plate_number,car_type_id) values ('KKK-002', select id from CAR_TYPE where name='ROKO')
insert into CAR (plate_number,car_type_id) values ('RRR-987', select id from CAR_TYPE where name='ESETKOCSI')
insert into CAR (plate_number,car_type_id) values ('HHH-002', select id from CAR_TYPE where name='KIM')

insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),8,16, select id from CAR where plate_number='HHH-001')
insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),9,17, select id from CAR where plate_number='KKK-002')
insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),6,18, select id from CAR where plate_number='RRR-987')
insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),18,6, select id from CAR where plate_number='HHH-002')

insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-5','SICK', select id from EMPLOYEE where name='Tanyasi Zoli')
insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-11','HOLIDAY', select id from EMPLOYEE where name='Tanyasi Zoli')
insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-12','HOLIDAY', select id from EMPLOYEE where name='Tanyasi Zoli')

insert into CAR_EVENT (DATE,TYPE,CAR_ID) values ('2018-11-11','SERVICE', select id from CAR where plate_number='HHH-001')
