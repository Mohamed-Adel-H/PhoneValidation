DROP view IF EXISTS customer_v;

CREATE VIEW customer_v
AS
SELECT id,
       name,
       substr(phone, instr(phone, '(') + 1, 3) as country_code,
       substr(phone, instr(phone, ')') + 2)    as state
FROM customer