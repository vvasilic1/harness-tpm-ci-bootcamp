#  Python API Test Script
import requests

def test_list_products():
    response = requests.get("http://localhost:8080/products")
    assert response.status_code == 200
    assert len(response.json()) == 2

def test_add_to_cart():
    response = requests.post("http://localhost:8080/products/cart/1")
    assert response.status_code == 200
    assert response.text == "Product added to cart"

def test_list_cart():
    test_add_to_cart()  # Ensure cart is not empty
    response = requests.get("http://localhost:8080/products/cart")
    assert response.status_code == 200
    assert len(response.json()) == 1

def test_pay_cart():
    test_add_to_cart()  # Ensure cart is not empty
    response = requests.post("http://localhost:8080/products/cart/pay")
    assert response.status_code == 200
    assert response.text == "Payment successful"

def run_tests():
    test_list_products()
    test_add_to_cart()
    test_list_cart()
    test_pay_cart()
    print("All tests passed.")

if __name__ == "__main__":
    run_tests()