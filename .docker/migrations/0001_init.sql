SELECT 'CREATE DATABASE mobile_phone_shop'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'mobile_phone_shop')\gexec