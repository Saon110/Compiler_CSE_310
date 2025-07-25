#pragma once;
#include<string>
#include<vector>

class str_list {
private:
    std::vector<std::string> variables;
    int line_number = 0;

public:
    // Constructor
    str_list() = default;

    // getters and setters
    const std::vector<std::string>& get_variables() const {
        return variables;
    }
    void set_variables(const std::vector<std::string>& vars) {
        variables = vars;
    }

    // Add a string to the list
    void add(const std::string& str) {
        variables.emplace_back(str);
    }

    void add(const std::vector<std::string>& strs) {
        for (const auto& str : strs) {
            variables.emplace_back(str);
        }
    }

    // Get the size of the list
    std::string size() const {
        return std::to_string(variables.size());
    }

    // Clear the list
    void clear() {
        variables.clear();
    }

    std::string get_list_as_string() const {
        std::string result;
        for (const auto& var : variables) {
            // if (!result.empty()) {
            //     result += ",";
            // }
            result += var;
        }
        return result;
    }

    // Get the line number
    int get_line_number() const {
        return line_number;
    }

    // Set the line number
    void set_line_number(int line_num) {
        line_number = line_num;
    }
    

};
