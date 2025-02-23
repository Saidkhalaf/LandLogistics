INSERT INTO truck_driver (name, birth_date)
VALUES
    ('John Smith',  '1985-06-15'),
    ('Emily Johnson','1990-12-22' ),
    ('Michael Brown', '1980-03-12'),
    ('Emma Williams' , '1985-01-30' ),
    ('James Jones', '1992-11-05');

INSERT INTO waiting_lists (date)
VALUES ('2024-10-03 10:30:00'),
                ('2024-10-04 12:00:00'),
                ('2024-10-05 08:45:00'),
                ('2024-10-06 14:00:00');

INSERT INTO seller_party (name, birth_date)
VALUES
    ('SteelCo Ltd.', '1975-06-15'),
    ('IronWorks Corp.', '1980-09-22'),
    ('CementSupplies Inc.', '1990-03-12'),
    ('PetroCokes Ltd.', '1985-01-30'),
    ('SlagTransporters BV', '1992-11-05');



-- Modify the seller_party table to use the sequence
-- ALTER table seller_party alter column id set default nextval('seller_party_id_seq');


INSERT INTO trucks (capacity, current_load, max_load, license_plate, seller_party_id, truck_driver_id, waiting_list_id, material_type)
VALUES
    (12000, 5000, 15000, 'ABC123', 1, 1, 1, 'GYPSUM'),
    (15000, 7000, 20000, 'XYZ789', 2, 2, 1, 'PETCOKES'),
    (18000, 8000, 22000, 'LMN456', 1, 3, 2, 'SLAG'),
    (20000, 10000, 25000, 'DEF321', 3, 1, 3, 'IRON_ORE'),
    (25000, 12000, 30000, 'GHI654', 2, 2, 2, 'CEMENT'),
    (22000, 15000, 28000, 'JKL987', 3, 3, 3, 'GYPSUM'),
    (19000, 6000, 24000, 'MNO543', 1, 1, 1, 'PETCOKES'),
    (17000, 9000, 23000, 'PQR876', 2, 2, 2, 'SLAG'),
    (16000, 4000, 21000, 'STU321', 3, 3, 3, 'IRON_ORE'),
    (14000, 2000, 20000, 'VWX159', 1, 1, 1, 'CEMENT');

-- CREATE SEQUENCE truck_id_seq
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;

-- ALTER TABLE trucks ALTER COLUMN id SET DEFAULT nextval('truck_id_seq');

insert into deliveries (delivery_date, material_type, status, warehouse_nr, weight)
values ('2024-10-09', 'CEMENT', 'COMPLETED', 101, 15000),
       ('2024-10-10', 'IRON_ORE', 'PENDING', 102, 20000),
       ('2024-10-11', 'SLAG', 'IN_PROGRESS', 103, 18000);

insert into delivery_appointment (actual_arrival_time, is_late, material_type, scheduled_arrival_time, status, weigh_bridge_identifier, delivery_id, seller_party_id, truck_id)
values
    ('2023-10-01T10:00:00', false, 'CEMENT', '2023-10-01T10:00:00', 'SCHEDULED', 'WB123', 1, 1, 6),
    ('2023-10-01T11:00:00', false, 'IRON_ORE', '2023-10-01T11:00:00', 'SCHEDULED', 'WB124', 2, 2, 2),
    ('2023-10-01T12:00:00', true, 'SLAG', '2023-10-01T12:00:00', 'LATE', 'WB125', 3, 3, 3),
    ('2023-10-01T13:00:00', false, 'PETCOKES', '2023-10-01T13:00:00', 'SCHEDULED', 'WB126', 3, 4, 4),
    ('2023-10-01T14:00:00', true, 'GYPSUM', '2023-10-01T14:00:00', 'LATE', 'WB127', 2, 5, 5);

insert into weigh_bridges (bridge_number, is_free,location)
values
    (1 , true, 'North Gate'),
    (2 , true, 'South Gate'),
    (3, true, 'East Gate'),
    (4, false, 'West Gate'),
    (5, false, 'Central Gate');

insert into wbt (gross_weight, net_weight, tare_weight, warehouse_number,weighing_moment, truck_id, weighbridge_id)
values
    (15000.0, 10000.0, 5000.0,19, '2024-10-09 08:30:00', 3, 1),
    (16000.0, 11000.0, 5000.0, 25,'2024-10-09 09:00:00', 4, 2),
    (17000.0, 12000.0, 5000.0, 30,'2024-10-09 10:00:00', 5, 3),
    (18000.0, 13000.0, 5000.0, 27,'2024-10-09 11:00:00', 6, 4),
    (19000.0, 14000.0, 5000.0, 20,'2024-10-09 12:00:00', 7, 5);