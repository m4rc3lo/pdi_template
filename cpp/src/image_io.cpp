#include "pdi/image_io.hpp"
#include <opencv2/imgcodecs.hpp>
#include <stdexcept>
namespace pdi {
cv::Mat read_image(const std::string& path){ cv::Mat image=cv::imread(path,cv::IMREAD_UNCHANGED); if(image.empty()) throw std::runtime_error("Nao foi possivel abrir a imagem: "+path); return image; }
void write_image(const std::string& path,const cv::Mat& image){ if(image.empty()) throw std::invalid_argument("A imagem de saida esta vazia."); if(!cv::imwrite(path,image)) throw std::runtime_error("Nao foi possivel salvar a imagem: "+path); }
}
