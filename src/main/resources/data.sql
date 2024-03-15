INSERT INTO "usertype" ("type")
SELECT 'customer'
WHERE NOT EXISTS (
    SELECT 1 FROM "usertype" WHERE "type" = 'customer'
);

INSERT INTO "usertype" ("type")
SELECT 'admin'
WHERE NOT EXISTS (
    SELECT 1 FROM "usertype" WHERE "type" = 'admin'
);

INSERT INTO "usertype" ("type")
SELECT 'superadmin'
WHERE NOT EXISTS (
    SELECT 1 FROM "usertype" WHERE "type" = 'superadmin'
);