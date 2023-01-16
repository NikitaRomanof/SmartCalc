#include "calc_model.h"

namespace s21 {

Model::Model(std::string initString) {
  if (initString.length() > 255) {
    throw std::overflow_error("String greater than 255 characters");
  } else {
    _inputString = initString;
    _input = nullptr;
    curr_tok = number;
    number_value = num0;
  }
}

Model::~Model() {}

typename Model::Token_value Model::get_token() {
  char ch;
  do {
    if (!_input->get(ch)) {
      return curr_tok = end;
    }
  } while (ch != '\n' && isspace(ch));
  switch (ch) {
    case 0:
      return curr_tok = end;
    case mul:
    case div:
    case plus:
    case minus:
    case mode:
    case poW:
    case sn:
    case cs:
    case tn:
    case atn:
    case asn:
    case acs:
    case sq:
    case lg:
    case ln:
    case lp:
    case rp:
      return curr_tok = Token_value(ch);
    case num0:
    case num1:
    case num2:
    case num3:
    case num4:
    case num5:
    case num6:
    case num7:
    case num8:
    case num9:
    case nump: {
      _input->putback(ch);
      *_input >> number_value;
      return curr_tok = number;
    }
    default:
      throw std::invalid_argument("Bad symbol");
  }
}

double Model::prim(bool get) {
  if (get) {
    get_token();
  }
  switch (curr_tok) {
    case number: {
      double v = number_value;
      get_token();
      return v;
    }
    case minus:
      return -prim(true);
    case plus:
      return prim(true);
    case sn:
      return sin(prim(true));
    case cs:
      return cos(prim(true));
    case tn:
      return tan(prim(true));
    case atn:
      return atan(prim(true));
    case asn:
      return asin(prim(true));
    case acs:
      return acos(prim(true));
    case sq:
      return sqrt(prim(true));
    case lg:
      return log10(prim(true));
    case ln:
      return log(prim(true));
    case lp: {
      double e = expr(true);
      if (curr_tok != rp) {
        throw std::logic_error("')' expected");
      }
      get_token();
      return e;
    }
    default:
      throw std::logic_error("primary expected");
  }
}

double Model::pw(bool get) {
  double left = prim(get);

  while (true) {
    if (curr_tok == poW) {
      left = pow(left, prim(true));
    } else {
      return left;
    }
  }
}

double Model::term(bool get) {
  double left = pw(get);

  while (true) {
    switch (curr_tok) {
      case mul:
        left *= pw(true);
        break;
      case div:
        if (double d = pw(true)) {
          left /= d;
          break;
        }
        throw std::logic_error("Divide by 0");
      case mode:
        if (double d = pw(true)) {
          left = fmod(left, d);
          break;
        }
        throw std::logic_error("Mode by 0");
      default:
        return left;
    }
  }
}

double Model::expr(bool get) {
  double left = term(get);

  for (;;) {
    switch (curr_tok) {
      case plus:
        left += term(true);
        break;
      case minus:
        left -= term(true);
        break;
      default:
        return left;
    }
  }
}

// std::string Model::rebuildString() {
//   size_t i = 0, n = 0;
//   int pos = 0;
//   std::string newStr(255, ' ');
//   bool error = true;
//   for (; i < _inputString.size();) {
//     if (_inputString[i] == 'm') {
//       if (_inputString[i + 1] == 'o' && _inputString[i + 2] == 'd') {
//         newStr[n] = 'm';
//         helperRebuild(i, n, pos, error, 3, true);
//       } else {
//         throw std::invalid_argument("Bad symbol");
//       }
//     } else if (_inputString[i] == 's') {
//       if (_inputString[i + 1] == 'i' && _inputString[i + 2] == 'n') {
//         newStr[n] = 's';
//         helperRebuild(i, n, pos, error, 3, false);
//       } else if (_inputString[i + 1] == 'q' && _inputString[i + 2] == 'r' &&
//                  _inputString[i + 3] == 't') {
//         newStr[n] = 'q';
//         helperRebuild(i, n, pos, error, 4, false);
//       }
//       errorFoo(_inputString, pos, error);
//     } else if (_inputString[i] == 'c') {
//       if (_inputString[i + 1] == 'o' && _inputString[i + 2] == 's') {
//         newStr[n] = 'c';
//         helperRebuild(i, n, pos, error, 3, false);
//       }
//       errorFoo(_inputString, pos, error);
//     } else if (_inputString[i] == 't') {
//       if (_inputString[i + 1] == 'a' && _inputString[i + 2] == 'n') {
//         newStr[n] = 't';
//         helperRebuild(i, n, pos, error, 3, false);
//       }
//       errorFoo(_inputString, pos, error);
//     } else if (_inputString[i] == 'a') {
//       if (_inputString[i + 1] == 't' && _inputString[i + 2] == 'a' &&
//           _inputString[i + 3] == 'n') {
//         newStr[n] = 'n';
//         helperRebuild(i, n, pos, error, 4, false);
//       } else if (_inputString[i + 1] == 's' && _inputString[i + 2] == 'i' &&
//                  _inputString[i + 3] == 'n') {
//         newStr[n] = 'i';
//         helperRebuild(i, n, pos, error, 4, false);
//       } else if (_inputString[i + 1] == 'c' && _inputString[i + 2] == 'o' &&
//                  _inputString[i + 3] == 's') {
//         newStr[n] = 'o';
//         helperRebuild(i, n, pos, error, 4, false);
//       }
//       errorFoo(_inputString, pos, error);
//     } else if (_inputString[i] == 'l') {
//       if (_inputString[i + 1] == 'n') {
//         newStr[n] = 'l';
//         helperRebuild(i, n, pos, error, 2, false);
//       } else if (_inputString[i + 1] == 'o' && _inputString[i + 2] == 'g') {
//         newStr[n] = 'g';
//         helperRebuild(i, n, pos, error, 3, false);
//       }
//       errorFoo(_inputString, pos, error);
//     } else if (_inputString[i] == 'n' || _inputString[i] == 'q' ||
//                _inputString[i] == 'i' || _inputString[i] == 'g' ||
//                _inputString[i] == 'o') {
//       throw std::invalid_argument("Bad symbol");
//     } else {
//       newStr[n] = _inputString[i];
//       helperRebuild(i, n, pos, error, 1, true);
//     }
//   }
//   newStr.erase(newStr.find_last_not_of(' ') + 1, std::string::npos);
//   return newStr;
// }

// void Model::helperRebuild(size_t& i, size_t& n, int& pos, bool& error,
//                           size_t step, bool flag) {
//   pos = i - 1;
//   i = i + step;
//   ++n;
//   error = flag;
// }

// void Model::errorFoo(std::string str, int pos, bool& error) {
//   if ((pos >= 0 && str[pos] == ')') ||
//       (pos >= 0 && (str[pos] >= '0' && str[pos] <= '9'))) {
//     error = true;
//   }
//   if (error == true) throw std::invalid_argument("Invalid string");
//   error = true;
// }

// void Model::checkStr(std::string str) {
//   int bo = 0, bc = 0, i = 0;
//   bool error = false;
//   std::string checkStr = "+-*/m^sctnioqgl()0123456789.";
//   std::basic_string<char>::size_type fi;
//   for (; i < (int)str.size(); i++) {
//     if (str[i] == '(') {
//       ++bo;
//       errorFoo(str, i - 1, error);
//       error = false;
//     }
//     if (str[i] == ')') {
//       ++bc;
//       if (i + 1 < (int)str.size() &&
//           ((str[i + 1] >= '0' && str[i + 1] <= '9') || str[i + 1] == '('))
//         throw std::invalid_argument("Invalid string");
//     }
//     fi = checkStr.find(str[i]);
//     if (fi == std::string::npos) throw std::invalid_argument("Bad symbol");
//   }
//   if (bo != bc || i > 255) {
//     throw std::invalid_argument("Bad brackets or string overflou");
//   }
// }

// void Model::checkDot(std::string str) {
//   for (size_t i = 0; i < str.size();) {
//     int countDot = 0;
//     while (i < str.size() &&
//            ((str[i] >= '0' && str[i] <= '9') || str[i] == '.')) {
//       ++i;
//       if (str[i] == '.') {
//         ++countDot;
//       }
//     }
//     if (countDot > 1) throw std::invalid_argument("Invalid string");
//     ++i;
//   }
//   //
// }

double Model::start() {
  std::string str = rebuildString();
  checkStr(str);
  checkDot(str);
  _input = new std::istringstream(str);
  double tmp = 0.0;
  while (_input) {
    get_token();
    if (curr_tok == end) {
      break;
    }
    tmp = expr(false);
  }
  if (_input != nullptr) {
    delete _input;
  }
  return tmp;
}

// std::vector<double> Model::credit_annuity(double sum_credit, double
// time_credit,
//                                           double per) {
//   double month_pay = 0.0, over_pay = 0.0, all_pay = 0.0;
//   double per_m = (per / 12.0) / 100;
//   month_pay = per_m * pow(1 + per_m, time_credit) /
//               (pow(1 + per_m, time_credit) - 1) * sum_credit;
//   over_pay = month_pay * time_credit - sum_credit;
//   all_pay = over_pay + sum_credit;
//   std::vector<double> rez = {month_pay, over_pay, all_pay};
//   return rez;
// }

// std::vector<double> Model::credit_dif(double sum_credit, double time_credit,
//                                       double per) {
//   double all_pay = 0.0, month_pay_last = 0.0, month_pay_first = 0.0,
//          over_pay = 0.0;
//   per = per / 100;
//   all_pay = 0.0;
//   double delta_m = sum_credit / time_credit;
//   double buf_sum = sum_credit;
//   month_pay_last = 0.0;
//   for (int i = 0; i < time_credit; i++) {
//     month_pay_last = buf_sum * per * 30.4166666666667 / 365 + delta_m;
//     buf_sum = buf_sum - delta_m;
//     all_pay = all_pay + month_pay_last;
//     if (!i) {
//       month_pay_first = month_pay_last;
//     }
//   }
//   over_pay = all_pay - sum_credit;
//   std::vector<double> rez = {month_pay_first, month_pay_last, over_pay,
//                              all_pay};
//   return rez;
// }

// double Model::get_re_list(std::map<double, double> r1, int count_month) {
//   double buf = 0.0;
//   std::map<double, double>::iterator it = r1.begin();
//   for (; it != r1.end(); ++it) {
//     int z = (int)it->first;
//     if (z == count_month) {
//       buf += it->second;
//     }
//   }
//   return buf;
// }

// double Model::get_wi_list(std::map<double, double> w1, int count_month,
//                           double all_sum) {
//   double buf = 0.0;
//   std::map<double, double>::iterator it = w1.begin();
//   for (; it != w1.end(); ++it) {
//     int z = (int)it->first;
//     if (z == count_month) {
//       buf += it->second;
//     }
//   }
//   if (buf > all_sum) buf = all_sum;
//   return buf;
// }

// std::vector<double> Model::deposit_calculator(std::map<double, double> r1,
//                                               std::map<double, double> w1,
//                                               double all_sum, double tern,
//                                               double interest, double tax,
//                                               int period_s, int kap) {
//   double all_interest = 0.0, all_tax = 0.0, all_sum_after = 0.0;
//   interest = interest / 100;
//   tax = tax / 100;
//   if (kap == 0) {
//     for (int i = 1; i <= tern; i++) {
//       all_sum = all_sum + get_re_list(r1, i);
//       all_sum = all_sum - get_wi_list(w1, i, all_sum);
//       all_interest += interest / 12 * all_sum;
//     }
//     all_sum_after = all_sum;
//   } else {
//     int p = 0;
//     for (int i = 1; i <= tern; i++) {
//       all_sum = all_sum + get_re_list(r1, i);
//       all_sum = all_sum - get_wi_list(w1, i, all_sum);
//       if (i % period_s == 0) {
//         all_interest += (interest / 12.0) * period_s * (all_sum +
//         all_interest); p = i;
//       } else if (i == tern) {
//         int z = i - p;
//         all_interest += (interest / 12.0) * z * (all_sum + all_interest);
//       }
//     }
//     all_sum_after = all_interest + all_sum;
//   }
//   all_tax = ((all_interest / tern) - ((1000000 * 0.11) / 12.0)) * tern * tax;
//   all_tax = all_tax < 0 ? 0.0 : all_tax;
//   std::vector<double> rez = {all_interest, all_tax, all_sum_after};
//   return rez;
// }

}  // namespace s21
