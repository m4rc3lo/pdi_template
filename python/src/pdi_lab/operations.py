import sys
from . import exit_code
KNOWN_OPERATIONS={"inspect","copy","channel_b","channel_g","channel_r","grayscale_average","grayscale_weighted","quantize","brightness","contrast","negative","threshold","histogram","convolution","mean_filter","weighted_mean","laplacian","sobel"}
def is_known_operation(operation:str)->bool:return operation in KNOWN_OPERATIONS
def run_operation(options)->int:
    print(f"Operacao '{options.operation}' reconhecida, mas ainda nao implementada no projeto-base.",file=sys.stderr)
    return exit_code.GENERAL_ERROR
