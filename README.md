# ActionModeCallback
<h3 id="CAB">Sử dụng chế độ hành động theo ngữ cảnh</h3>
Chế độ hành động theo ngữ cảnh là một triển khai hệ thống <code><a href="https://developer.android.com/reference/android/view/ActionMode.html?hl=vi">ActionMode</a></code> tập trung vào tương tác người dùng hướng tới việc thực hiện các hành động theo ngữ cảnh. Khi một người dùng kích hoạt chế độ này bằng cách chọn một mục, một <em>thanh hành động ngữ cảnh</em> sẽ xuất hiện bên trên màn hình để trình bày các hành động mà người dùng có thể thực hiện trên (các) mục đang được chọn. Trong khi chế độ này được kích hoạt, người dùng có thể chọn nhiều mục (nếu bạn cho phép), bỏ chọn mục, và tiếp tục điều hướng trong hoạt động (miễn là bạn sẵn lòng cho phép). Chế độ hành động bị vô hiệu hóa và thanh hành động ngữ cảnh biến mất khi người dùng bỏ chọn tất cả các mục, nhấn nút QUAY LẠI, hoặc chọn hành động <em>Xong</em> ở phía bên trái của thanh.
<p class="note"><strong>Lưu ý:</strong> Thanh hành động ngữ cảnh không nhất thiết phải được liên kết với <a href="https://developer.android.com/guide/topics/ui/actionbar.html?hl=vi">thanh hành động</a>. Chúng vận hành độc lập, mặc dù thanh hành động ngữ cảnh đè lên vị trí của thanh hành động về mặt hiển thị.</p>
Nếu bạn đang phát triển cho phiên bản Android 3.0 (API mức 11) hoặc cao hơn, bạn nên sử dụng chế độ hành động theo ngữ cảnh để trình bày các hành động ngữ cảnh, thay vì sử dụng <a href="https://developer.android.com/guide/topics/ui/menus.html?hl=vi#FloatingContextMenu">menu ngữ cảnh nổi</a>.

Đối với các dạng xem cung cấp hành động ngữ cảnh, bạn nên thường xuyên gọi ra chế độ hành động theo ngữ cảnh khi xảy ra một trong hai sự kiện sau (hoặc cả hai):
<ul>
 	<li>Người dùng thực hiện nhấp giữ trên dạng xem.</li>
 	<li>Người dùng chọn một hộp kiểm hoặc một thành phần UI tương tự trong dạng xem.</li>
</ul>
Cách ứng dụng của bạn gọi ra chế độ hành động theo ngữ cảnh và định nghĩa hành vi cho từng hành động phụ thuộc vào thiết kế của bạn. Cơ bản có hai thiết kế:
<ul>
 	<li>Đối với các hành động ngữ cảnh trên các dạng xem riêng lẻ, tùy ý.</li>
 	<li>Đối với các hành động ngữ cảnh hàng loạt trên các nhóm mục trong một <code><a href="https://developer.android.com/reference/android/widget/ListView.html?hl=vi">ListView</a></code> hoặc <code><a href="https://developer.android.com/reference/android/widget/GridView.html?hl=vi">GridView</a></code> (cho phép người dùng chọn nhiều mục và thực hiện một hành động trên tất cả).</li>
</ul>
Các phần sau mô tả phần thiết lập cần thiết đối với từng kịch bản

<img src="https://github.com/trongcong/ActionModeCallback/blob/master/device-2017-04-04-212539.png"/>
