#include <gtest/gtest.h>

#include <iostream>

#include "calc_model.h"

TEST(CalcModel, num) {
  std::string str = "10.12345";
  s21::Model model(str);
  double rez = model.start();
  double check = 10.12345;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, plus) {
  std::string str = "1+2";
  s21::Model model(str);
  double rez = model.start();
  double check = 1 + 2;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, minus) {
  std::string str = "1-2";
  s21::Model model(str);
  double rez = model.start();
  double check = 1 - 2;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, div_br) {
  std::string str = "2/(1+1)";
  s21::Model model(str);
  double rez = model.start();
  double check = 2 / (1 + 1);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, mul_br) {
  std::string str = "2*(5-2)";
  s21::Model model(str);
  double rez = model.start();
  double check = 2 * (5 - 2);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, pow_) {
  std::string str = "2^3+2^2";
  s21::Model model(str);
  double rez = model.start();
  double check = 8 + 4;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, mod_) {
  std::string str = "4mod2";
  s21::Model model(str);
  double rez = model.start();
  double check = 0;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, br_br) {
  std::string str = "((((10))))";
  s21::Model model(str);
  double rez = model.start();
  double check = 10;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, sqrt_) {
  std::string str = "sqrt9";
  s21::Model model(str);
  double rez = model.start();
  double check = 3;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, mod_2) {
  std::string str = "0mod5";
  s21::Model model(str);
  double rez = model.start();
  double check = 0;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, log10_1) {
  std::string str = "log10";
  s21::Model model(str);
  double rez = model.start();
  double check = 1;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, ln_) {
  std::string str = "ln2.718281828459045";
  s21::Model model(str);
  double rez = model.start();
  double check = 1;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, minus_minus) {
  std::string str = "---5";
  s21::Model model(str);
  double rez = model.start();
  double check = -5;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, minus_plus) {
  std::string str = "--++5";
  s21::Model model(str);
  double rez = model.start();
  double check = 5;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, minus_2) {
  std::string str = "5-10";
  s21::Model model(str);
  double rez = model.start();
  double check = -5;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, mul_2) {
  std::string str = "0*3";
  s21::Model model(str);
  double rez = model.start();
  double check = 0;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, inf_) {
  std::string str = "0^(-1)";
  s21::Model model(str);
  double rez = model.start();
  double check = pow(0, -1);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, minus_inf_) {
  std::string str = "ln0";
  s21::Model model(str);
  double rez = model.start();
  double check = log(0);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, minus_zero_) {
  std::string str = "-0";
  s21::Model model(str);
  double rez = model.start();
  double check = 0.0;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, sin_1) {
  std::string str = "sin(3.141592653589793/2)";
  s21::Model model(str);
  double rez = model.start();
  double check = sin(3.141592653589793 / 2);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, sin_2) {
  std::string str = "sin(0)";
  s21::Model model(str);
  double rez = model.start();
  double check = sin(0);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, sin_3) {
  std::string str = "-sin(3.141592653589793/2)";
  s21::Model model(str);
  double rez = model.start();
  double check = -sin(3.141592653589793 / 2);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, cos_1) {
  std::string str = "cos(3.141592653589793)";
  s21::Model model(str);
  double rez = model.start();
  double check = cos(3.141592653589793);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, cos_2) {
  std::string str = "cos(0)";
  s21::Model model(str);
  double rez = model.start();
  double check = cos(0.0);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, cos_3) {
  std::string str = "cos(3.141592653589793/2)+10*-1";
  s21::Model model(str);
  double rez = model.start();
  double check = cos(3.141592653589793 / 2) + 10 * -1;
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, tan_1) {
  std::string str = "tan(0)";
  s21::Model model(str);
  double rez = model.start();
  double check = tan(0);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, tan_2) {
  std::string str = "-tan(3.141592653589793/4)";
  s21::Model model(str);
  double rez = model.start();
  double check = -tan(3.141592653589793 / 4);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, tan_3) {
  std::string str = "tan(3.141592653589793/2)";
  s21::Model model(str);
  double rez = model.start();
  double check = tan(3.141592653589793 / 2);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, atan_1) {
  std::string str = "atan(0)";
  s21::Model model(str);
  double rez = model.start();
  double check = atan(0);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, asin_1) {
  std::string str = "asin(0)";
  s21::Model model(str);
  double rez = model.start();
  double check = asin(0);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, asin_2) {
  std::string str = "asin(-1)";
  s21::Model model(str);
  double rez = model.start();
  double check = asin(-1);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, acos_1) {
  std::string str = "acos(0)";
  s21::Model model(str);
  double rez = model.start();
  double check = acos(0);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, acos_2) {
  std::string str = "acos(1)";
  s21::Model model(str);
  double rez = model.start();
  double check = acos(1);
  ASSERT_EQ(rez, check);
}

TEST(CalcModel, credit_1) {
  double sum_credit1 = 1000000;
  double time_credit1 = 12.0;
  double per1 = 15;
  std::vector<double> check = {90258.30, 83099.75, 1083099.75};
  s21::Model model("");
  std::vector<double> rez =
      model.credit_annuity(sum_credit1, time_credit1, per1);
  ASSERT_FLOAT_EQ(check[0], rez[0]);
  ASSERT_FLOAT_EQ(rez[1], check[1]);
  ASSERT_FLOAT_EQ(rez[2], check[2]);
}

TEST(CalcModel, credit_2) {
  double sum_credit1 = 1000000;
  double time_credit1 = 12.0;
  double per1 = 15;
  std::vector<double> check = {95833.33, 84375.00, 81250.00, 1081250.00};
  s21::Model model("");
  std::vector<double> rez = model.credit_dif(sum_credit1, time_credit1, per1);
  ASSERT_FLOAT_EQ(check[0], rez[0]);
  ASSERT_FLOAT_EQ(rez[1], check[1]);
  ASSERT_FLOAT_EQ(rez[2], check[2]);
}

TEST(CalcModel, deposit_1) {
  double all_sum = 1000000;
  double tern = 12;
  double interest = 15;
  double tax = 13;
  int period_s = 1;
  int kap = 0;
  std::map<double, double> r1;
  r1.insert({0.0, 0.0});
  std::map<double, double> w1;
  w1.insert({0.0, 0.0});
  s21::Model model("");
  std::vector<double> rez = model.deposit_calculator(
      r1, w1, all_sum, tern, interest, tax, period_s, kap);

  std::vector<double> check = {150000, 5200, 1000000};
  ASSERT_FLOAT_EQ(check[0], rez[0]);
  ASSERT_FLOAT_EQ(rez[1], check[1]);
  ASSERT_FLOAT_EQ(rez[2], check[2]);
}

TEST(CalcModel, deposit_2) {
  double all_sum = 1000000;
  double tern = 12;
  double interest = 15;
  double tax = 13;
  int period_s = 6;
  int kap = 1;
  std::map<double, double> r1;
  r1.insert({1.0, 100000.0});
  std::map<double, double> w1;
  w1.insert({10.0, 1000.0});
  s21::Model model("");
  std::vector<double> rez = model.deposit_calculator(
      r1, w1, all_sum, tern, interest, tax, period_s, kap);

  std::vector<double> check = {171112.5, 7944.625, 1270112.00};
  ASSERT_FLOAT_EQ(check[0], rez[0]);
  ASSERT_FLOAT_EQ(rez[1], check[1]);
  ASSERT_FLOAT_EQ(rez[2], check[2]);
}

int main(int argc, char **argv) {
  ::testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
