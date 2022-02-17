- git : version control system.
### Commands
>- ls : 현재 선택할 수 있는 목록들을 보여줌

>- ls -l : line by line으로 보여줌 / ls -a : 숨김파일까지도 보여줌

>- cd : 현재 위치를 이동하고자 할 때 / cd .. : 상튀폴더로 이동 / cd ~ : home으로 이동

>- pwd : 현재 내 위치 확인

>- mkdir : directory 생성

>- touch : 파일 생성

>- mv 옮길 파일 옮길 장소 : 파일을 이동시킨다.

>- cp 복제할 파일 복제할 장소 : 파일을 복제장소로 이동시킨다. 
   - 복제할 장소를 .. 으로 하면 상위 폴더로 이동됨.
   
>- rm 삭제할 파일 : 파일을 삭제한다. 
   - rm fileName.* -> 이 fileName을 가진 모든 파일을 삭제해라.
   
>- rm -r 삭제할 폴더 : 폴더를 삭제한다.
파일명을 변경할때 : mv fileName changedfileName -> filaName의 이름을 changedfileName로 바꿔라. (cp명령어로도 가능)

>- normal 모드에서 set nu : index를 맨 앞에 보여준다.

>- cat fileName : 파일 내용을 출력한다.

>- vi 파일명 : 파일을 읽기 모드로 open한다.

>-- h j k l - left, down, up, right

>- i - insert mode

>- v - visual mode

>- ESC - back to normal mode

>- d - delete

>- dd - delete a line

>- y - yank(copy)

>- yy - yank a line

>- p - paste

>- u - undo

>- ctrl + r - reback

>- a - append

>- A - append from end of line

>- o - open line(under) and append

>- O - open line(upper) and append

>- H - move to the top of the screen

>- L - move to the bottom of the screen

>- :q - quit

>- :q! - quit discarding all changes

>- :w - write

>- :wq - write and quit

>- :{number} - jump to {number}th line.

>- i -> 파일을 쓸 수 있다.