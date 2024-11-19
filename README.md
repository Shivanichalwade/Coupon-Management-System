# Coupon Management System

### Table of Contents
1. Introduction
2. Getting Started
3. Steps 
4. How it workd
5. Implemented cases
6. API endpoints
7. Unimplemented cases

### Welcome!
This Coupon Management System is here to simplify how coupons are managed. Whether it's offering cart-wide discounts, product-specific deals, or the classic "Buy X Get Y Free" offers, this system shows you how to manage everything in one place.

### Getting Started
* Java Development Kit (JDK) - Version 11 or higher.
* Maven - To build the project.
* Spring Boot - For running the application.
* MongoDB database - To store cart and coupon data.

### Steps:
1. Clone the repository:
   git clone https://github.com/Shivanichalwade/Coupon-Management-System.git
2. Build the project:
   mvn clean install
3. Start your MongoDB service:
   mongod
4. Run the application:
   mvn spring-boot:run
5. Open your swagger or Postman to interact with the API.
   Base URL: http://localhost:8080/api/

### How It Works
The system uses REST APIs to handle coupon-related tasks and manage cart operations. With Spring Boot and MongoDB.

## Implemented Cases
1. Cart Management
Manage cart operations:
* Create a new cart.
* Fetch details of a specific cart.
* Retrieve all carts.
* Update cart details.
* Delete a cart.
  
2. Coupon Management
Handle various coupon-related tasks:
* Create new coupons.
* Fetch a specific coupon by its code.
* Retrieve all available coupons.
* Update an existing coupon.
* Delete a coupon by its code.

3. Coupon Validation
* Validate if a coupon is applicable based on cart details.

4. Apply Coupon to Cart
* Apply a specific coupon to a cart and calculate the resulting discount.

5. Fetch Applicable Coupons
* Retrieve all coupons applicable to a given cart, ensuring users see the best available options.

#### 1. Cart-Wide Discounts
Apply percentage discounts when the total cart value meets a specific threshold.

#### 2. Product-Specific Discounts
Define exclusive discounts for selected products.

#### 3. Buy X Get Y Free Offers
Provide free items when a customer buys a certain quantity of specific products.

#### 4. Coupon Validation
Automatically check if a coupon is valid and applicable to a given cart.

#### 5. Fetch and Apply Applicable Coupons
Help users maximize savings by showing and applying the most suitable coupons.

## Cart APIs
1. Create a Cart
POST /api/carts

2. Fetch Cart by ID
GET /api/carts/{id}

3. Fetch All Carts
GET /api/carts

4. Update a Cart
PUT /api/carts/{id}

5. Delete a Cart
DELETE /api/carts/{id}

## Coupon APIs
1. Create a Coupon
POST /api/coupons

2. Fetch a Coupon
GET /api/coupons/{code}

3. Fetch All Coupons
GET /api/coupons

4. Update a Coupon
PUT /api/coupons/{code}

5. Delete a Coupon
DELETE /api/coupons/{code}

6. Get Applicable Coupons for a Cart
POST /api/coupons/applicableCoupons

7. Apply a Coupon to a Cart
POST /api/coupons/applyCoupon/{id}

## Unimplemented Cases
1. New User Coupon
If user is newly registerd give him/her welcome coupon.

2. Referral Coupon
Coupon applied if they refer their friends/family.

3. Coupons on specific bank cards
Discount coupons on specific credit cards.

## Limitations
Only One Coupon per Cart
Only a single coupon can be applied at a time.

Discount Types
Only have cart-wide, product-specific, and BxGy offers.

## Assumptions
Product details are same and consistent during the application.
Each user has only one active cart.
