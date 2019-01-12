insert into JOB (name) values ('DRIVER')
insert into JOB (name) values ('DOCTOR')
insert into JOB (name) values ('MEDIC')

insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-01-01', 176)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-02-01', 160)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-03-01', 160)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-04-01', 160)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-05-01', 176)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-06-01', 152)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-07-01', 184)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-08-01', 168)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-09-01', 168)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-10-01', 176)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-11-01', 160)
insert into MONTHLY_WORKING_HOURS (month, monthly_working_hour) values ('2019-12-01', 160)

insert into EMPLOYEE (name,job_id,night,contract_percent) values ('Vizi Marci', select id from JOB where name='DRIVER', false, 100)
insert into EMPLOYEE (name,job_id,night,contract_percent) values ('Hegyi Józsi', select id from JOB where name='DOCTOR', false, 100)
insert into EMPLOYEE (name,job_id,night,contract_percent) values ('Kuti Béla', select id from JOB where name='MEDIC', false, 100)
insert into EMPLOYEE (name,job_id,night,contract_percent) values ('Tanyasi Zoli', select id from JOB where name='DRIVER', false, 100)
insert into EMPLOYEE (name,job_id,night,contract_percent) values ('Városi Matyi', select id from JOB where name='DOCTOR', false, 100)
insert into EMPLOYEE (name,job_id,night,contract_percent) values ('Földi Elek', select id from JOB where name='MEDIC', false, 100)

insert into CAR_TYPE (name) values ('KIM')
insert into CAR_TYPE (name) values ('ROKO')
insert into CAR_TYPE (name) values ('ESETKOCSI')
insert into CAR_TYPE (name) values ('ONE')

insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='KIM', select id from JOB where name='DRIVER')
insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='KIM', select id from JOB where name='DOCTOR')
insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='KIM', select id from JOB where name='MEDIC')
insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='ESETKOCSI', select id from JOB where name='MEDIC')
insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='ESETKOCSI', select id from JOB where name='DRIVER')
insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='ONE', select id from JOB where name='DRIVER')
insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='ONE', select id from JOB where name='DOCTOR')
insert into CAR_TYPE_JOBS (car_type_id, jobs_id) values (select id from CAR_TYPE where name='ONE', select id from JOB where name='MEDIC')

insert into SHIFT (date,start,end,car_type_id) values (CURRENT_DATE(),8,16, select id from CAR_TYPE where name='ESETKOCSI')
insert into SHIFT (date,start,end,car_type_id) values (CURRENT_DATE(),9,17, select id from CAR_TYPE where name='ESETKOCSI')
insert into SHIFT (date,start,end,car_type_id) values (CURRENT_DATE(),6,18, select id from CAR_TYPE where name='KIM')
insert into SHIFT (date,start,end,car_type_id) values (CURRENT_DATE(),18,6, select id from CAR_TYPE where name='KIM')

insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-5','SICK', select id from EMPLOYEE where name='Tanyasi Zoli')
insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-11','HOLIDAY', select id from EMPLOYEE where name='Tanyasi Zoli')
insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-12','HOLIDAY', select id from EMPLOYEE where name='Tanyasi Zoli')
