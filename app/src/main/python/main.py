import sys
import io
import math
from types import ModuleType
import threading

class CodeExecutionTimeout(Exception):
    pass

def run_with_timeout(code, globals_env, timeout=3):
    def target():
        exec(code, globals_env)

    thread = threading.Thread(target=target)
    thread.start()
    thread.join(timeout)
    if thread.is_alive():
        raise CodeExecutionTimeout("Code execution timed out.")

def eval(code):
    old_stdout = sys.stdout
    old_stderr = sys.stderr
    sys.stdout = io.StringIO()
    sys.stderr = io.StringIO()

    # 허용된 math 함수들을 포함한 모듈 생성
    allowed_math = ModuleType("math")
    allowed_math.sqrt = math.sqrt
    allowed_math.sin = math.sin
    allowed_math.cos = math.cos
    allowed_math.tan = math.tan
    allowed_math.pi = math.pi
    allowed_math.e = math.e
    allowed_math.log = math.log
    allowed_math.exp = math.exp
    allowed_math.ceil = math.ceil
    allowed_math.floor = math.floor
    allowed_math.fabs = math.fabs
    allowed_math.pow = math.pow

    # 허용할 built-in 함수
    allowed_builtins = {
        "print": print,
        "len": len,
        "range": range,
        "sum": sum,
        "abs": abs,
        "round": round,
        "all": all,
        "any": any,
        "sorted": sorted,
        "min": min,
        "max": max,
        "int": int,
        "float": float,
        "str": str,
        "dict": dict,
        "set": set,
        "tuple": tuple,
        "bool": bool,
        "zip": zip,
        "enumerate": enumerate,
        "pow": pow,
        "reversed": reversed,
        "filter": filter,
        "map": map,
        "divmod": divmod,
        "chr": chr,
        "ord": ord,
        "hex": hex,
        "bin": bin,
        "oct": oct,
        "__build_class__": __build_class__
    }

    # 실행 환경 설정 (새로 생성)
    exec_globals = {
        "__builtins__": allowed_builtins,  # 제한된 내장 함수만 제공
        "math": allowed_math,             # 제한된 math 모듈 제공
        "__name__": "__main__"
    }

    try:
        run_with_timeout(code, exec_globals, timeout=3)

        output = sys.stdout.getvalue().strip()
        error = sys.stderr.getvalue().strip()

        if error:
            return f"Error: {error}"  # 오류 반환
        elif output:
            return output  # 출력 반환
        else:
            return "Code executed successfully, but no output."  # 결과 없음
    except CodeExecutionTimeout:
        return "Error: Code execution timed out."  # 시간 초과 메시지 반환
    except Exception as e:
        return f"Error: {str(e)}"  # 다른 오류 반환
    finally:
        # 표준 출력 및 표준 오류 복원
        sys.stdout = old_stdout
        sys.stderr = old_stderr
