#ifndef SRC_CALC_MODEL_H_
#define SRC_CALC_MODEL_H_

#include <cctype>
#include <cmath>
#include <iostream>
#include <map>
#include <sstream>
#include <string>
#include <vector>

namespace s21 {
class Model {
 private:
  enum Token_value : char {
    number,
    end,
    plus = '+',
    minus = '-',
    mul = '*',
    div = '/',
    mode = 'm',
    poW = '^',
    sn = 's',
    cs = 'c',
    tn = 't',
    atn = 'n',
    asn = 'i',
    acs = 'o',
    sq = 'q',
    lg = 'g',
    ln = 'l',
    lp = '(',
    rp = ')'
  };

  enum Number_value : char {
    num0 = '0',
    num1 = '1',
    num2 = '2',
    num3 = '3',
    num4 = '4',
    num5 = '5',
    num6 = '6',
    num7 = '7',
    num8 = '8',
    num9 = '9',
    nump = '.',
  };

  std::istream* _input;
  std::string _inputString;
  Token_value curr_tok;
  double number_value;
  Token_value get_token();
  double prim(bool get);
  double term(bool get);
  double pw(bool get);
  double expr(bool get);
  std::string rebuildString();
  void helperRebuild(size_t& i, size_t& n, int& pos, bool& error, size_t step,
                     bool flag);
  void checkStr(std::string str);
  void errorFoo(std::string str, int pos, bool& error);
  void checkDot(std::string str);
  double get_re_list(std::map<double, double> r1, int count_month);
  double get_wi_list(std::map<double, double> w1, int count_month,
                     double all_sum);

 public:
  explicit Model(std::string initString);
  ~Model();
  double start();
  std::vector<double> credit_annuity(double sum_credit, double time_credit,
                                     double per);
  std::vector<double> credit_dif(double sum_credit, double time_credit,
                                 double per);
  std::vector<double> deposit_calculator(std::map<double, double> r1,
                                         std::map<double, double> w1,
                                         double all_sum, double tern,
                                         double interest, double tax,
                                         int period_s, int kap);
};

}  // namespace s21

#endif  // SRC_CALC_MODEL_H_
