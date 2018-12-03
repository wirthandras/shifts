insert into EMPLOYEE (name,job,night) values ('Vizi Marci','DRIVER', false)
insert into EMPLOYEE (name,job,night) values ('Hegyi Józsi','DOCTOR', false)
insert into EMPLOYEE (name,job,night) values ('Kuti Béla','MEDIC', false)
insert into EMPLOYEE (name,job,night) values ('Tanyasi Zoli','DRIVER', false)
insert into EMPLOYEE (name,job,night) values ('Városi Matyi','DOCTOR', false)
insert into EMPLOYEE (name,job,night) values ('Földi Elek','MEDIC', false)

insert into CAR (plate_number,car_type) values ('HHH-001','KIM')
insert into CAR (plate_number,car_type) values ('KKK-002','ROKO')
insert into CAR (plate_number,car_type) values ('RRR-987','ESETKOCSI')
insert into CAR (plate_number,car_type) values ('HHH-002','KIM')

insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),8,16, select id from CAR where plate_number='HHH-001')
insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),9,17, select id from CAR where plate_number='KKK-002')
insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),6,18, select id from CAR where plate_number='RRR-987')
insert into SHIFT (date,start,end,car_id) values (CURRENT_DATE(),18,6, select id from CAR where plate_number='HHH-002')

insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-5','SICK', select id from EMPLOYEE where name='Tanyasi Zoli')
insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-11','HOLIDAY', select id from EMPLOYEE where name='Tanyasi Zoli')
insert into EMPLOYEE_EVENT (DATE,TYPE,EMPLOYEE_ID) values ('2018-11-12','HOLIDAY', select id from EMPLOYEE where name='Tanyasi Zoli')

insert into CAR_EVENT (DATE,TYPE,CAR_ID) values ('2018-11-11','SERVICE', select id from CAR where plate_number='HHH-001')
